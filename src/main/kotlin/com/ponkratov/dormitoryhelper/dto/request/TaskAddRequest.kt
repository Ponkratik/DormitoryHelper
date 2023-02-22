package com.ponkratov.dormitoryhelper.dto.request

import org.springframework.format.annotation.DateTimeFormat
import java.util.Date

data class TaskAddRequest(
    val name: String,
    val description: String,
    val endDate: Date,
    val userPublishedId: Long
)