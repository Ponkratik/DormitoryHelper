package com.ponkratov.dormitoryhelper.dto.mapper

import com.ponkratov.dormitoryhelper.dto.request.RegisterRequest
import com.ponkratov.dormitoryhelper.model.Role
import com.ponkratov.dormitoryhelper.model.RoleEnum
import com.ponkratov.dormitoryhelper.model.User

fun RegisterRequest.toUser(): User {
    return User(
        fio = fio,
        email = email,
        password = password,
        login = login,
        dormitory = dormitory,
        room = room,
        group = group,
        roleByRoleId = Role(5, RoleEnum.ROLE_STUDENT.name)
    )
}