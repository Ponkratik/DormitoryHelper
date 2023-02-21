package com.ponkratov.dormitoryhelper.dto.request

data class TransactionAddRequest(
    val userIdFrom: Long,
    val userIdTo: Long,
    val reward: Int,
    val comment: String
)
