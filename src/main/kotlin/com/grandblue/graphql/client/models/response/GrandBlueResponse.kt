package com.grandblue.graphql.client.models.response

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class GrandBlueResponse(
  val `data`: List<GrandBlueBaseResponse>,
  val metadata: MutableMap<String, Any>
) {

  fun addMetadata(key: String, value: Any) : GrandBlueResponse {
    this.metadata[key] = value
    return this
  }

  companion object {
    fun initialize(responseData: List<GrandBlueBaseResponse>) : GrandBlueResponse {
      val currentTimeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss"))
      val staticMetadata = mutableMapOf<String, Any>("timestamp" to currentTimeStamp)

      return GrandBlueResponse(data = responseData, staticMetadata)
    }
  }
}