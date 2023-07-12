package com.grandblue.graphql.client.constants

object GraphQLDocumentMappings {
  const val GET_PRODUCT_LIST = "getProducts"
  const val GET_PRODUCT_BY_PRODUCT_ID = "getProduct"

  const val GET_DIVER_LIST = "getDivers"
  const val GET_DIVER_BY_DIVER_ID = "getDiver"
  const val ADD_DIVER = "addDiver"
}

object ProductModelVariables {
  const val PRODUCT_ID = "productId"
}

object DiverModelVariables {
  const val DIVER_ID = "diverId"
  const val DIVER_NAME = "diverName"
  const val DIVING_LEVEL = "divingLevel"
  const val DIVING_LICENSE_NUMBER = "divingLicenseNumber"
}