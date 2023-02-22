package com.ponkratov.dormitoryhelper.repository

import com.ponkratov.dormitoryhelper.model.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : JpaRepository<Transaction, Long> {

    fun getAllByUserByUserTo_Id(userByUserTo_id: Long): List<Transaction>

    @Query("select COALESCE(sum(t.reward), 0) from Transaction t where t.userByUserTo.id=:userId")
    fun getOptQuantityByUserId(userId: Long): Int
}