package com.ponkratov.dormitoryhelper.controller

import com.ponkratov.dormitoryhelper.dto.request.TransactionAddRequest
import com.ponkratov.dormitoryhelper.model.Transaction
import com.ponkratov.dormitoryhelper.service.TransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/transaction")
class TransactionController {

    @Autowired
    private var _transactionService: TransactionService? = null
    private val transactionService get() = requireNotNull(_transactionService)

    @GetMapping("/get/all")
    fun getAllTransactions(): ResponseEntity<List<Transaction>> {
        val result = transactionService.getAll()
        return ResponseEntity.ok(result)
    }

    @GetMapping("/get/{id}")
    fun getTransactionsByUserId(@PathVariable id: Long): ResponseEntity<List<Transaction>> {
        val result = transactionService.getAllByUserId(id)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/get/{id}/quantity")
    fun getOptQuantityByUserId(@PathVariable id: Long): ResponseEntity<Int> {
        val result = transactionService.getOptQuantityByUserId(id)
        return ResponseEntity.ok(result)
    }

    @PostMapping("/add")
    fun addTransaction(@RequestBody @Validated transactionAddRequest: TransactionAddRequest): ResponseEntity<String> {
        val result = transactionService.addTransaction(transactionAddRequest)
        return ResponseEntity.ok(result)
    }
}