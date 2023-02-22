package com.ponkratov.dormitoryhelper.controller

import com.ponkratov.dormitoryhelper.dto.request.SpawnDutiesRequest
import com.ponkratov.dormitoryhelper.model.Duty
import com.ponkratov.dormitoryhelper.service.DutyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/duty")
class DutyController {

    @Autowired
    private var _dutyService: DutyService? = null
    private val dutyService get() = requireNotNull(_dutyService)

    @GetMapping("/get/all")
    fun getDuties(): ResponseEntity<List<Duty>> {
        val result = dutyService.getAll()
        return ResponseEntity.ok(result)
    }

    @GetMapping("/get/{id}")
    fun getDutiesByUserId(@PathVariable id: Long): ResponseEntity<List<Duty>> {
        val result = dutyService.getAllByUserId(id)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/get/dormitory/{dormitory}/{floor}/{month}/{year}")
    fun getActiveTasksByDormitory(
        @PathVariable dormitory: Long,
        @PathVariable floor: Long,
        @PathVariable month: Int,
        @PathVariable year: Int
    ): ResponseEntity<List<Duty>> {
        val result = dutyService.getDutiesByDormitoryAndFloor(dormitory, floor, month, year)
        return ResponseEntity.ok(result)
    }

    @PostMapping("/add/{dormitory}/{month}")
    fun addDuties(@PathVariable dormitory: Long, @PathVariable month: Int): ResponseEntity<String> {
        val result = dutyService.addDuties(dormitory, month)
        return ResponseEntity.ok(result)
    }

    @PostMapping("/pick/{dutyId}/{userId}")
    fun pickDuty(@PathVariable dutyId: Long, @PathVariable userId: Long): ResponseEntity<String> {
        val result = dutyService.pickDuty(dutyId, userId)
        return ResponseEntity.ok(result)
    }

    @PostMapping("/finish/{dutyId}")
    fun finishDuty(@PathVariable dutyId: Long): ResponseEntity<String> {
        val result = dutyService.finishDuty(dutyId)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/get/{id}/quantity")
    fun getDutiesQuantityByUserId(@PathVariable id: Long): ResponseEntity<Int> {
        val result = dutyService.getDutyQuantityByUserId(id)
        return ResponseEntity.ok(result)
    }

    @PostMapping("/spawn")
    fun spawnDuties(@RequestBody spawnDutiesRequest: SpawnDutiesRequest): ResponseEntity<Boolean> {
        val result = dutyService.spawnDuties(spawnDutiesRequest)
        return ResponseEntity.ok(result)
    }
}