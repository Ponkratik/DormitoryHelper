package com.ponkratov.dormitoryhelper.security.service

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(
    val id: Long = 0,
    val fio: String = "",
    val email: String = "",
    private val password: String = "",
    val login: String = "",
    val dormitory: Long = 0,
    val room: String = "",
    val group: String = "",
    val roles: Collection<GrantedAuthority>,
) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> = roles

    //Java-conflict
    override fun getPassword(): String = password

    override fun getUsername(): String = login

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}