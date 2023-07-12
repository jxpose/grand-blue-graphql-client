package com.grandblue.graphql.client.validator

import com.grandblue.graphql.client.models.request.ValidatedRequest
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Component
class GrandBlueValidator : Validator {
  override fun supports(clazz: Class<*>): Boolean {
    return ValidatedRequest::class.java.isAssignableFrom(clazz)
  }

  override fun validate(target: Any, errors: Errors) {
    val request: ValidatedRequest = target as ValidatedRequest
    request.validate(errors)
  }
}