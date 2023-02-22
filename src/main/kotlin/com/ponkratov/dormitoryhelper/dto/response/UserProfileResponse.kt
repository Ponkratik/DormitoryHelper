package com.ponkratov.dormitoryhelper.dto.response

import com.ponkratov.dormitoryhelper.model.User

data class UserProfileResponse (
    val user: User,
    val dutiesCount: Int,
    val optCount: Int
)