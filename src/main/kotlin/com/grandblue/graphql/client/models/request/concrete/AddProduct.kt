package com.grandblue.graphql.client.models.request.concrete

import com.grandblue.graphql.client.constants.ValidationErrorCodes
import com.grandblue.graphql.client.models.request.ValidatedRequest
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils

data class AddProduct(val productName: String ="", val productDescription: String = "") : ValidatedRequest {
  override fun validate(errors: Errors) {
    ValidationUtils.rejectIfEmpty(errors, "productName", ValidationErrorCodes.REQUIRED)
  }
}