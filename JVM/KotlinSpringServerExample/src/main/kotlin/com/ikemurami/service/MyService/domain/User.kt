package com.ikemurami.service.domain

import org.springframework.security.core.userdetails.UserDetails
import com.ikemurami.service.domain.dto.UserDto
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.OneToMany
import javax.persistence.Table


@Entity
@Table(name = "user", schema = "auth")
class User : UserDetails, BaseEntity() {
    @Column(name = "user_name")
    private lateinit var username: String

    override fun getUsername(): String {
        return username
    }

    fun setUsername(username: String) {
        this.username = username
    }
}

fun User.toDto(): UserDto {
    return UserDto(
        uuid,
        username
    )
}