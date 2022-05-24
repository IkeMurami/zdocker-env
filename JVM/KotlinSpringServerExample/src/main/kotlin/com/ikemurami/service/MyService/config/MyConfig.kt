package com.ikemurami.service.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import java.util.*


@Configuration
@ConditionalOnProperty(prefix = "ikemurami", value = ["some"], havingValue = "true")
class MyConfig {
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(MyConfig::class.java)
    }

    @Value("\${services.frontend-url}")
    private val frontUrl: String = ""

    @Value("\${services.frontend-url:\"\"}}")
    private val frontUrlSecond: String? = null

    @Bean
    fun someParam(): String {
        logger.debug("Message")
        return ""
    }
}