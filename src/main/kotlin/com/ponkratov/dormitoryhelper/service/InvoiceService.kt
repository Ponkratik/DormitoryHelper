package com.ponkratov.dormitoryhelper.service

import com.ponkratov.dormitoryhelper.dto.request.InvoiceBatchRequest
import com.ponkratov.dormitoryhelper.dto.request.SpawnInvoicesRequest
import com.ponkratov.dormitoryhelper.model.Invoice
import com.ponkratov.dormitoryhelper.repository.InvoiceRepository
import com.ponkratov.dormitoryhelper.repository.RoleRepository
import com.ponkratov.dormitoryhelper.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Calendar

@Service
class InvoiceService {

    @Autowired
    private var _invoiceRepository: InvoiceRepository? = null
    private val invoiceRepository get() = requireNotNull(_invoiceRepository)

    @Autowired
    private var _userRepository: UserRepository? = null
    private val userRepository get() = requireNotNull(_userRepository)

    @Autowired
    private var _roleRepository: RoleRepository? = null
    private val roleRepository get() = requireNotNull(_roleRepository)

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

    fun spawnPayments(spawnInvoicesRequest: SpawnInvoicesRequest): Boolean {
        try {
            val studentRole = roleRepository.findRoleById(5)
            val users = userRepository.findAllByRoleByRoleId(studentRole)

            val dateInvoiced = Calendar.getInstance().time

            users.forEach { user ->
                val invoice = Invoice(
                    month = spawnInvoicesRequest.month,
                    year = spawnInvoicesRequest.year,
                    dateInvoiced = dateInvoiced,
                    sum = if (user.dormitory == 4L) spawnInvoicesRequest.cost4 else spawnInvoicesRequest.cost123,
                    userByUserId = user
                )
                invoiceRepository.save(invoice)
            }
            return true
        } catch (_: Exception) {
            return false
        }
    }
}