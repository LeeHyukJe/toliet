package com.wisenut.domain.model.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User?, Long?> {
    fun findByUsername(username: String?): User?
    fun findByEmailAddress(emailAddress: String?): User?
}