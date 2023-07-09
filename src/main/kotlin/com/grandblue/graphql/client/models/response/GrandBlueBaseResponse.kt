package com.grandblue.graphql.client.models.response

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.grandblue.graphql.client.models.response.concrete.Diver
import com.grandblue.graphql.client.models.response.concrete.Product

@JsonSubTypes(
  JsonSubTypes.Type(value = Product::class),
  JsonSubTypes.Type(value = Diver::class),
)
abstract class GrandBlueBaseResponse {

}