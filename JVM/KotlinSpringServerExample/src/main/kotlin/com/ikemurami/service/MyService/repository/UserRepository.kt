package com.ikemurami.service.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import com.ikemurami.service.domain.User
import java.util.*


@Repository
interface UserRepository : CrudRepository<User, UUID> {
    fun findUserByUserName(userName: String): User
}