package com.ponkratov.dormitoryhelper.dto.request

data class InvoiceBatchRequest(
    val login: String,
    val year: Int,
    val month: Int
)