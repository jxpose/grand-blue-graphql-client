package com.grandblue.graphql.client

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GraphqlClientApplication

fun main(args: Array<String>) {
	runApplication<GraphqlClientApplication>(*args)
}
