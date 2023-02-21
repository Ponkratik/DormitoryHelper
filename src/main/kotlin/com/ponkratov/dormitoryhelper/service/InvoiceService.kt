package com.ponkratov.dormitoryhelper.service

import com.ponkratov.dormitoryhelper.dto.request.InvoiceBatchRequest
import com.ponkratov.dormitoryhelper.model.Invoice
import com.ponkratov.dormitoryhelper.repository.InvoiceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class InvoiceService {

    @Autowired
    private var _invoiceRepository: InvoiceRepository? = null
    private val invoiceRepository get() = requireNotNull(_invoiceRepository)

    fun getAll(): List<Invoice> {
        return invoiceRepository.findAll()
    }

    fun getAllByUserId(userId: Long): List<Invoice> {
        return invoiceRepository.getAllByUserByUserId_Id(userId)
    }

    fun loadPayments(batch: List<InvoiceBatchRequest>): List<String> {
        val errors: MutableList<String> = mutableListOf()

        batch.forEach {invoice ->
            invoiceRepository.loadInvoicePayment(invoice.login, invoice.year, invoice.month)
                .also {
                    if (it == 0L) errors.add(invoice.login)
                }
        }

        return errors
    }
}