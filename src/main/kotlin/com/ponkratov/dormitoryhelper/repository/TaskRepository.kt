package com.ponkratov.dormitoryhelper.repository

import com.ponkratov.dormitoryhelper.model.Task
import com.ponkratov.dormitoryhelper.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
interface TaskRepository: JpaRepository<Task, Long> {

    @Query("select t from Task t where t.userByPublishedId.dormitory=:dormitoryNumber and t.endDate<current_timestamp " +
            "and t.userByUserPicked=null")
    fun getActiveTasksByDormitory(dormitoryNumber: Long): List<Task>

    @Modifying
    @Transactional
    @Query("update Task t set t.userByUserPicked=:user where t.id=:taskId")
    fun pickTask(taskId: Long, user: User): Int

    @Modifying
    @Transactional
    @Query("update Task t set t.finished=true where t.id=:taskId")
    fun finishTask(taskId: Long): Long

    fun getAllByUserByUserPicked_Id(userByUserPicked_id: Long): List<Task>
}