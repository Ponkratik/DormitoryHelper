package com.ponkratov.dormitoryhelper.dto.request

import java.math.BigDecimal

class SpawnInvoicesRequest {
    var month: Int = 0
    var year: Int = 0
    lateinit var cost123: BigDecimal
    lateinit var cost4: BigDecimal
}