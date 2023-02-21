package com.ponkratov.dormitoryhelper.service

import com.ponkratov.dormitoryhelper.dto.request.TransactionAddRequest
import com.ponkratov.dormitoryhelper.model.Transaction
import com.ponkratov.dormitoryhelper.repository.TransactionRepository
import com.ponkratov.dormitoryhelper.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TransactionService {

    @Autowired
    private var _transactionRepository: TransactionRepository? = null
    private val transactionRepository get() = requireNotNull(_transactionRepository)

    @Autowired
    private var _userRepository: UserRepository? = null
    private val userRepository get() = requireNotNull(_userRepository)

    fun getAll(): List<Transaction> {
        return transactionRepository.findAll()
    }

    fun getAllByUserId(userId: Long): List<Transaction> {
        return transactionRepository.getAllByUserByUserTo_Id(userId)
    }

    fun getOptQuantityByUserId(userId: Long): Int {
        return transactionRepository.getOptQuantityByUserId(userId)
    }

    fun addTransaction(transactionRequest: TransactionAddRequest): String {
        val userFrom = userRepository.getUserById(transactionRequest.userIdFrom)
        val userTo = userRepository.getUserById(transactionRequest.userIdTo)

        with(transactionRequest) {
            transactionRepository.save(Transaction(
                reward = reward,
                comment = comment,
                userByUserFrom = userFrom,
                userByUserTo = userTo
            ))
        }

        return "Transaction added successfully"
    }
}