package com.grandblue.graphql.client.controller

import com.grandblue.graphql.client.exceptions.ResourceNotFoundException
import com.grandblue.graphql.client.models.response.GrandBlueErrorResponse
import com.grandblue.graphql.client.validator.GrandBlueValidator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class GrandBlueGlobalController {

  private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

  @InitBinder
  fun initBinder(webDataBinder: WebDataBinder) {
    webDataBinder.addValidators(GrandBlueValidator())
  }

  @ExceptionHandler(MethodArgumentNotValidException::class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  fun methodArgumentNotValidException(exception: MethodArgumentNotValidException) : GrandBlueErrorResponse {
    val errors = exception.bindingResult.fieldErrors.map { "${it.field} : ${it.code}" }
    return GrandBlueErrorResponse(errors)
  }

  @ExceptionHandler(ResourceNotFoundException::class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  fun resourceNotFoundException(exception: ResourceNotFoundException) { }

  @ExceptionHandler(Exception::class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  fun unexpectedException(exception: Exception) {
    logger.error("Unexpected error occurred. Caused by: ${exception.message}", exception)
  }
}