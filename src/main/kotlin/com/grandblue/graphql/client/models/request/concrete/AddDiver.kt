package com.grandblue.graphql.client.models.request.concrete

import com.grandblue.graphql.client.constants.ValidationErrorCodes
import com.grandblue.graphql.client.enums.DivingLevelType
import com.grandblue.graphql.client.models.request.ValidatedRequest
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils

data class AddDiver(val diverName: String ="", val divingLevel: Int = 1, val divingLicenseNumber: String = "") : ValidatedRequest {
  override fun validate(errors: Errors) {
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "diverName", ValidationErrorCodes.REQUIRED)
    DivingLevelType.of(divingLevel) ?: errors.rejectValue("divingLevel", ValidationErrorCodes.INVALID)
  }
}