package com.ponkratov.dormitoryhelper.service

import com.ponkratov.dormitoryhelper.model.RoleEnum
import com.ponkratov.dormitoryhelper.model.User
import com.ponkratov.dormitoryhelper.repository.RoleRepository
import com.ponkratov.dormitoryhelper.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    private val passwordEncoder: PasswordEncoder? = null

    @Autowired
    private var _userRepository: UserRepository? = null
    private val userRepository get() = requireNotNull(_userRepository)

    @Autowired
    private val _roleRepository: RoleRepository? = null
    private val roleRepository get() = requireNotNull(_roleRepository)

    fun registerUser(user: User): String {

        if (userRepository.findByLogin(user.login) != null) {
            return "User with login ${user.email} exists"
        }

        user.password = requireNotNull(passwordEncoder).encode(user.password)
        user.roleByRoleId = roleRepository.findByName(RoleEnum.ROLE_STUDENT.name)

        val registerResult = userRepository.save(user)

        return "User was registered successfully"
    }

    fun getUserById(id: Long): User {
        return userRepository.getUserById(id)
    }

    fun getUsers(): List<User> {
        return userRepository.findAll()
    }
}