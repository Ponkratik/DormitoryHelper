package com.ponkratov.dormitoryhelper.dto.request

class RegisterRequest(
    var id: Long = 0,
    var fio: String = "",
    var email: String = "",
    var password: String = "",
    var login: String = "",
    var dormitory: Long = 0,
    var room: String = "",
    var group: String = "",
    var role: String = ""
)