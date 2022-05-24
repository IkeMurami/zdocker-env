package com.ikemurami.service.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

import com.ikemurami.service.interfaces.LoginService


@RequestMapping("/some")
@Api(value = "User", tags = ["User auth"], description = "My description")
@ConditionalOnProperty(prefix = "ikemurami.security", value = ["allow-login", havingValue = "true"])
class LoginController(
    private val loginService: LoginService
) {

    @PostMapping("/signin")
    @ApiResponses(
        value = [
            ApiResponse(code = 400, message = "Smt wrong"),
            ApiResponse(code = 422, message = "Wrong user/pass")
        ]
        fun login(
            @ApiParam("Username") @RequestParam username: String,
            @ApiParam("Password") @RequestParam password: String
        ): String {
            return loginService!!.login(username, password)
        }
    )
}