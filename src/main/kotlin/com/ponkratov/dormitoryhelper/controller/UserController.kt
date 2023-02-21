package com.ponkratov.dormitoryhelper.controller

import com.ponkratov.dormitoryhelper.dto.mapper.toUser
import com.ponkratov.dormitoryhelper.dto.request.LoginRequest
import com.ponkratov.dormitoryhelper.dto.request.RegisterRequest
import com.ponkratov.dormitoryhelper.dto.response.JwtResponse
import com.ponkratov.dormitoryhelper.dto.response.ObjectResponse
import com.ponkratov.dormitoryhelper.model.User
import com.ponkratov.dormitoryhelper.security.jwt.JwtUtils
import com.ponkratov.dormitoryhelper.security.service.UserDetailsImpl
import com.ponkratov.dormitoryhelper.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
class UserController {

    @Autowired
    private var _userService: UserService? = null
    private val userService get() = requireNotNull(_userService)

    @Autowired
    private var _authenticationManager: AuthenticationManager? = null
    private val authenticationManager get() = requireNotNull(_authenticationManager)

    @Autowired
    private var _jwtUtils: JwtUtils? = null
    private val jwtUtils get() = requireNotNull(_jwtUtils)

    @PostMapping("/register")
    fun registerUser(@RequestBody @Validated registerRequest: RegisterRequest): ResponseEntity<*> {
        val result = userService.registerUser(registerRequest.toUser())

        return ResponseEntity.ok(ObjectResponse<String>(result))
    }

    @PostMapping("/login")
    fun login(@RequestBody @Validated loginRequest: LoginRequest): ResponseEntity<*> {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(loginRequest.login, loginRequest.password)
        )
        SecurityContextHolder.getContext().authentication = authentication
        val jwt = jwtUtils.generateJwtToken(authentication)
        val userDetails = authentication.principal as UserDetailsImpl
        val roles = userDetails.authorities
            .stream()
            .map { item: GrantedAuthority -> item.authority }
            .collect(Collectors.toList())

        return ResponseEntity.ok().body(
            JwtResponse(
                jwt,
                userDetails.id,
                userDetails.fio,
                userDetails.email,
                userDetails.login,
                userDetails.dormitory,
                userDetails.room,
                userDetails.group,
                roles.first()
            )
        )
    }
    @GetMapping("/get/all")
    fun getUserResponses(): ResponseEntity<List<User>> {
        val result = userService.getUsers()
        return ResponseEntity.ok(result)
    }

    @GetMapping("/get/{id}")
    fun getUserResponse(@PathVariable id: Long): ResponseEntity<User> {
        val result = userService.getUserById(id)
        return ResponseEntity.ok(result)
    }
}