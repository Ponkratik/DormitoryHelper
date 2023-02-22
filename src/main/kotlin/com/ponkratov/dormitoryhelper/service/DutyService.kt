package com.ponkratov.dormitoryhelper.service

import com.ponkratov.dormitoryhelper.dto.request.SpawnDutiesRequest
import com.ponkratov.dormitoryhelper.model.Duty
import com.ponkratov.dormitoryhelper.repository.DutyRepository
import com.ponkratov.dormitoryhelper.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.YearMonth
import java.util.*

@Service
class DutyService {

    @Autowired
    private var _dutyRepository: DutyRepository? = null
    private val dutyRepository get() = requireNotNull(_dutyRepository)

    @Autowired
    private var _userRepository: UserRepository? = null
    private val userRepository get() = requireNotNull(_userRepository)

    fun getAll(): List<Duty> {
        return dutyRepository.findAll()
    }

    fun getAllByUserId(userId: Long): List<Duty> {
        return dutyRepository.getAllByUserByUserId_Id(userId)
    }

    fun pickDuty(dutyId: Long, userId: Long): String {
        val userToPick = userRepository.getUserById(userId)
        val result = dutyRepository.pickDuty(dutyId, userToPick)

        return if (result > 0) {
            "Duty picked successfully"
        } else {
            "Error while picking duty"
        }
    }

    fun getDutyQuantityByUserId(userId: Long): Int {
        return dutyRepository.countAllByUserByUserId_Id(userId)
    }

    fun finishDuty(dutyId: Long): String {
        val result = dutyRepository.finishDuty(dutyId)

        return if (result > 0) {
            "Duty finished successfully"
        } else {
            "Error while finishing duty"
        }
    }

    fun getDutiesByDormitoryAndFloor(dormitoryNumber: Long, floor: Long, month: Int, year: Int): List<Duty> {
        val lastDay = YearMonth.of(year, month).lengthOfMonth()

        val monthStart = Calendar.getInstance()
        monthStart.set(year, month - 1, 1, 0, 0)
        val monthEnd = Calendar.getInstance()
        monthEnd.set(year, month - 1, lastDay, 23, 59)

        return dutyRepository.getAllByDormitoryAndFloorAndStartDateAfterAndStartDateBefore(
            dormitory = dormitoryNumber,
            floor = floor,
            startDate = monthStart.time,
            startDate2 = monthEnd.time
        )
    }

    fun addDuties(dormitoryNumber: Long, month: Int): String {
        /*val result = 1
        val year = if (month < YearMonth.now().month.value) {
            var d = Year.now().value
            ++d
        } else {
            Year.now().value
        }
        val days = YearMonth.of(year, month).lengthOfMonth()
        for (i in 1..days) {
            val date = LocalDateTime.of(year, month, i, 8, 0, 0)
        }

        return if (result > 0) {
            "Duties added successfully"
        } else {
            "Error while adding duties"
        } */
        return "Duties added successfully"
    }

    fun spawnDuties(spawnDutiesRequest: SpawnDutiesRequest): Boolean {
        try {
            with(spawnDutiesRequest) {
                val lastDay = YearMonth.of(year, month).lengthOfMonth()
                for (day in 1..lastDay) {
                    val start = Calendar.getInstance()
                    start.set(year, month - 1, day, 8, 0)
                    val finish = Calendar.getInstance()
                    finish.set(year, month - 1, day, 16, 0)

                    dutyRepository.save(
                        Duty(
                            dormitory = dormitory,
                            floor = floor,
                            startDate = start.time,
                            endDate = finish.time
                        )
                    )

                    start.set(year, month - 1, day, 16, 0)
                    finish.set(year, month - 1, day, 0, 0)
                    finish.add(Calendar.DATE, 1)

                    dutyRepository.save(
                        Duty(
                            dormitory = dormitory,
                            floor = floor,
                            startDate = start.time,
                            endDate = finish.time
                        )
                    )
                }
            }
            return true
        } catch (_: Exception) {
            return false
        }
    }

}