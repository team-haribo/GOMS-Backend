package com.project.goms

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class GomsApplication

fun main(args: Array<String>) {
	runApplication<GomsApplication>(*args)
}
