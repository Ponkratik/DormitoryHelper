package com.ponkratov.dormitoryhelper.controller

import com.ponkratov.dormitoryhelper.dto.request.InvoiceBatchRequest
import com.ponkratov.dormitoryhelper.model.Invoice
import com.ponkratov.dormitoryhelper.service.InvoiceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/invoice")
class InvoiceController {

    @Autowired
    private var _invoiceService: InvoiceService? = null
    private val invoiceService get() = requireNotNull(_invoiceService)

    @GetMapping("/get/all")
    fun getInvoices(): ResponseEntity<List<Invoice>> {
        val result = invoiceService.getAll()
        return ResponseEntity.ok(result)
    }

    @GetMapping("/get/{id}")
    fun getInvoicesByUserId(@PathVariable id: Long): ResponseEntity<List<Invoice>> {
        val result = invoiceService.getAllByUserId(id)
        return ResponseEntity.ok(result)
    }

    @PostMapping("/login")
    fun login(@RequestBody @Validated invoiceBatch: List<InvoiceBatchRequest>): ResponseEntity<List<String>> {
        val result = invoiceService.loadPayments(invoiceBatch)
        return ResponseEntity.ok(result)
    }
}