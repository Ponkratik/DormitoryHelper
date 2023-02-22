package com.ponkratov.dormitoryhelper.service

import com.ponkratov.dormitoryhelper.dto.request.TaskAddRequest
import com.ponkratov.dormitoryhelper.model.Task
import com.ponkratov.dormitoryhelper.repository.TaskRepository
import com.ponkratov.dormitoryhelper.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TaskService {

    @Autowired
    private var _taskRepository: TaskRepository? = null
    private val taskRepository get() = requireNotNull(_taskRepository)

    @Autowired
    private var _userRepository: UserRepository? = null
    private val userRepository get() = requireNotNull(_userRepository)

    fun getAll(): List<Task> {
        return taskRepository.findAll()
    }

    fun getAllByUserId(userId: Long): List<Task> {
        return taskRepository.getAllByUserByUserPicked_Id(userId)
    }

    fun getActiveTasksByDormitory(dormitoryNumber: Long): List<Task> {
        return taskRepository.getActiveTasksByDormitory(dormitoryNumber)
    }

    fun pickTask(taskId: Long, userId: Long): String {
        val userToPick = userRepository.getUserById(userId)
        val result = taskRepository.pickTask(taskId, userToPick)

        return if (result > 0) {
            "Task picked successfully"
        } else {
            "Error while picking task"
        }
    }

    fun addTask(taskAddRequest: TaskAddRequest): String {
        val userPublished = userRepository.getUserById(taskAddRequest.userPublishedId)

        with(taskAddRequest) {
            taskRepository.save(Task(
                name = name,
                description = description,
                publicationDate = Date(),
                endDate = endDate,
                userByPublishedId = userPublished
            ))
        }

        return "Task added successfully"
    }

    fun finishTask(taskId: Long): String {
        val result = taskRepository.finishTask(taskId)

        return if (result > 0) {
            "Task picked successfully"
        } else {
            "Error while picking task"
        }
    }
}