package com.grandblue.graphql.client.models.response.concrete

import com.grandblue.graphql.client.models.response.GrandBlueBaseResponse
import java.io.Serializable

data class Diver(
  val diverId: String,
  val diverName: String,
  val divingLevel: Int,
  val divingLicenseNumber: String
) : GrandBlueBaseResponse(), Serializable {

  companion object {
    private const val serialVersionUID: Long = 1L
  }
}