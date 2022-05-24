package com.ikemurami.service.exceptions

import org.springframework.security.core.AuthenticationException

class MyException(message: String): AuthenticationException(message)