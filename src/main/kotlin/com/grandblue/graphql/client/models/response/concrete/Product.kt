package com.grandblue.graphql.client.models.response.concrete

import com.grandblue.graphql.client.models.response.GrandBlueBaseResponse
import java.io.Serializable

data class Product(
  val productId: String,
  val productName: String,
  val productDescription: String,
  val deleteFlag: Boolean
) : GrandBlueBaseResponse(), Serializable {

  companion object {
    private const val serialVersionUID: Long = 1L
  }
}

