package com.project.goms.domain.outing.usecase

import com.project.goms.global.annotation.UseCase
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

@UseCase
class ValidateOutingTimeUseCase {

    fun execute(): Boolean {
        val currentDate = LocalDate.now()
        val currentTime = LocalTime.now()
        val startTime = LocalTime.of(18, 50)
        val endTime = LocalTime.of(19, 25)

        if (currentDate.dayOfWeek != DayOfWeek.WEDNESDAY || (currentTime.isBefore(startTime) || currentTime.isAfter(endTime))) {
            return false
        }
        return true
    }

}