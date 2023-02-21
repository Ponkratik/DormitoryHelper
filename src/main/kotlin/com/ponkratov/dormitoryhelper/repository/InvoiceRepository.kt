package com.ponkratov.dormitoryhelper.repository

import com.ponkratov.dormitoryhelper.model.Invoice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
@Repository
interface InvoiceRepository : JpaRepository<Invoice, Long> {
    fun getAllByUserByUserId_Id(userByUserId_id: Long): List<Invoice>

    @Modifying
    @Query("update Invoice i set i.paymentDate=CURRENT_DATE where i.userByUserId.login=:login and i.year=:year and i.month=:month")
    fun loadInvoicePayment(login: String, year: Int, month: Int): Long
}