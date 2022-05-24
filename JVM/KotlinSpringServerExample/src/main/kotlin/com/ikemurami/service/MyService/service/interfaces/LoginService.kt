package com.ikemurami.service.interfaces

interface LoginService {
    fun login(username: String, password: String): String
    fun logout()
}