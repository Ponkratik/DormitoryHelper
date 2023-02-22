package com.ponkratov.dormitoryhelper.controller

import com.ponkratov.dormitoryhelper.dto.request.TaskAddRequest
import com.ponkratov.dormitoryhelper.model.Task
import com.ponkratov.dormitoryhelper.service.TaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/task")
class TaskController {
    
    @Autowired
    private var _taskService: TaskService? = null
    private val taskService get() = requireNotNull(_taskService)

    @GetMapping("/get/all")
    fun getTasks(): ResponseEntity<List<Task>> {
        val result = taskService.getAll()
        return ResponseEntity.ok(result)
    }

    @GetMapping("/get/{id}")
    fun getTasksByUserId(@PathVariable id: Long): ResponseEntity<List<Task>> {
        val result = taskService.getAllByUserId(id)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/get/dormitory/{id}")
    fun getActiveTasksByDormitory(@PathVariable id: Long): ResponseEntity<List<Task>> {
        val result = taskService.getActiveTasksByDormitory(id)
        return ResponseEntity.ok(result)
    }

    @PostMapping("/add")
    fun addTask(@RequestBody @Validated taskAddRequest: TaskAddRequest): ResponseEntity<String> {
        val result = taskService.addTask(taskAddRequest)
        return ResponseEntity.ok(result)
    }

    @PostMapping("/pick/{taskId}/{userId}")
    fun pickTask(@PathVariable taskId: Long, @PathVariable userId: Long): ResponseEntity<String> {
        val result = taskService.pickTask(taskId, userId)
        return ResponseEntity.ok(result)
    }

    @PostMapping("/finish/{taskId}")
    fun pickTask(@PathVariable taskId: Long): ResponseEntity<String> {
        val result = taskService.finishTask(taskId)
        return ResponseEntity.ok(result)
    }
}