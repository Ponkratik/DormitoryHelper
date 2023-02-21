package com.ponkratov.dormitoryhelper.dto.response

class JwtResponse(
    var accessToken: String,
    var id: Long,
    var fio: String,
    var email: String,
    var login: String,
    var dormitory: Long,
    var room: String,
    var group: String,
    var role: String
) {
    private var tokenType = "Bearer"
}