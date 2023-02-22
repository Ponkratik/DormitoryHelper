package com.ponkratov.dormitoryhelper.repository

import com.ponkratov.dormitoryhelper.model.Duty
import com.ponkratov.dormitoryhelper.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface DutyRepository : JpaRepository<Duty, Long> {

    fun getAllByDormitoryAndFloor(dormitory: Long, floor: Long): List<Duty>

    @Modifying
    @Query("update Duty d set d.userByUserId=:user where d.id=:dutyId")
    fun pickDuty(dutyId: Long, user: User): Long

    fun countAllByUserByUserId_Id(userByUserId_id: Long): Int

    fun getAllByUserByUserId_Id(userByUserId_id: Long): List<Duty>

    @Modifying
    @Query("update Duty d set d.finished=true where d.id=:dutyId")
    fun finishDuty(dutyId: Long): Long
}