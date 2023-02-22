package com.ponkratov.dormitoryhelper.repository

import com.ponkratov.dormitoryhelper.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<Role, Long> {

    fun findAllByName(name: String): Set<Role>
    fun findByName(name: String): Role

    fun findRoleById(id: Long): Role
}