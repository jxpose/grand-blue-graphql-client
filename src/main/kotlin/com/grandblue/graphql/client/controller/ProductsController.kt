package com.grandblue.graphql.client.controller

import com.grandblue.graphql.client.constants.AppConstant
import com.grandblue.graphql.client.constants.GraphQLDocumentMappings
import com.grandblue.graphql.client.constants.ProductModelVariables
import com.grandblue.graphql.client.models.response.GrandBlueResponse
import com.grandblue.graphql.client.models.response.concrete.Product
import org.slf4j.LoggerFactory
import org.springframework.graphql.client.FieldAccessException
import org.springframework.graphql.client.HttpGraphQlClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/products")
class ProductsController {

  private val logger = LoggerFactory.getLogger(this.javaClass)

  @GetMapping
  fun getProducts() : Mono<GrandBlueResponse> {
    val webClient = WebClient.builder().baseUrl(AppConstant.SERVER_URL).build()
    val graphQlClient = HttpGraphQlClient.builder(webClient).build()

    return graphQlClient.documentName(GraphQLDocumentMappings.GET_PRODUCT_LIST)
      .retrieve(GraphQLDocumentMappings.GET_PRODUCT_LIST)
      .toEntityList(Product::class.java)
      .onErrorResume(FieldAccessException::class.java) { ex ->
        val response = ex.response
        val field = ex.field

        logger.error("Error occurred when parsing the response. Caused by: $response and $field")

        return@onErrorResume Mono.error(ex)
      }.map {
        GrandBlueResponse.initialize(it).addMetadata("count", it.size)
      }
  }

  @GetMapping("/{product-id}")
  fun getProduct(@PathVariable("product-id") productId: String) : Mono<GrandBlueResponse> {
    val webClient = WebClient.builder().baseUrl(AppConstant.SERVER_URL).build()
    val graphQlClient = HttpGraphQlClient.builder(webClient).build()

    return graphQlClient.documentName(GraphQLDocumentMappings.GET_PRODUCT_BY_PRODUCT_ID)
      .variable(ProductModelVariables.PRODUCT_ID, productId)
      .retrieve(GraphQLDocumentMappings.GET_PRODUCT_BY_PRODUCT_ID)
      .toEntity(Product::class.java)
      .onErrorResume(FieldAccessException::class.java) { ex ->
        val response = ex.response
        val field = ex.field

        logger.error("Error occurred when parsing the response. Caused by: $response and $field")

        return@onErrorResume Mono.error(ex)
      }.map { GrandBlueResponse.initialize(listOf(it)) }
  }

  private fun getGraphQlClient() : HttpGraphQlClient {
    val webClient = WebClient.builder().baseUrl(AppConstant.SERVER_URL).build()
    return HttpGraphQlClient.builder(webClient).build()
  }
}