package com.ponkratov.dormitoryhelper

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class DormitoryHelperApplication

fun main(args: Array<String>) {
    runApplication<DormitoryHelperApplication>(*args)
}
