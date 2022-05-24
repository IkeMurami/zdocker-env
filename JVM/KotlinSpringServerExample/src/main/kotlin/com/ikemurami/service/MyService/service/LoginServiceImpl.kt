package com.ikemurami.service

import org.springframework.stereotype.Service
import com.ikemurami.service.repository.UserRepository

@Service
class LoginServiceImpl(
    private val userRepository: UserRepository
) : LoginService {

    override fun login(username: String, password: String): String {
        TODO("not implemented")
    }

    override fun logout() {
        TODO("not implemented")
    }
}