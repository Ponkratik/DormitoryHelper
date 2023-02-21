package com.ponkratov.dormitoryhelper.repository

import com.ponkratov.dormitoryhelper.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByEmail(email: String): User?
    fun findByLogin(login: String): User?
    fun getUserById(id: Long): User
}