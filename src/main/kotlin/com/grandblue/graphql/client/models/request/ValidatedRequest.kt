package com.grandblue.graphql.client.models.request

import org.springframework.validation.Errors

fun interface ValidatedRequest {
  fun validate(errors: Errors)
}