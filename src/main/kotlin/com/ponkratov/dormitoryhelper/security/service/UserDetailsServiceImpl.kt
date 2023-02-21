package com.ponkratov.dormitoryhelper.security.service

import com.ponkratov.dormitoryhelper.model.User
import com.ponkratov.dormitoryhelper.model.mapper.toDetails
import com.ponkratov.dormitoryhelper.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserDetailsServiceImpl : UserDetailsService {

    @Autowired
    var userRepository: UserRepository? = null

    @Transactional
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(login: String): UserDetails {
        val user: User = userRepository?.findByLogin(login)
            ?: throw UsernameNotFoundException("User was not found with login: $login")
        return user.toDetails()
    }


}