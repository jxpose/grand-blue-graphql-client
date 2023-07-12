package com.grandblue.graphql.client.enums

enum class DivingLevelType(val divingLevel: Int) {
  BEGINNER(1),
  INTERMEDIATE(2),
  ADVANCED(3),
  PROFESSIONAL(4);

  companion object {
    fun of(divingLevel: Int) : DivingLevelType? {
      return values().firstOrNull { it.divingLevel == divingLevel }
    }
  }
}