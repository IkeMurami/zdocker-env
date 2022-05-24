package com.ikemurami.service.MyService

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MyServiceApplication

fun main(args: Array<String>) {
	runApplication<MyServiceApplication>(*args)
}
