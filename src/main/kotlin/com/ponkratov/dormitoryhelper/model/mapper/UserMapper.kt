package com.ponkratov.dormitoryhelper.model.mapper

import com.ponkratov.dormitoryhelper.model.User
import com.ponkratov.dormitoryhelper.security.service.UserDetailsImpl
import org.springframework.security.core.authority.SimpleGrantedAuthority

fun User.toDetails(): UserDetailsImpl = UserDetailsImpl(
    id = id,
    fio = fio,
    email = email,
    password = password,
    roles = setOf(SimpleGrantedAuthority(roleByRoleId.name)),
    login = login,
    dormitory = dormitory,
    room = room,
    group = group
)