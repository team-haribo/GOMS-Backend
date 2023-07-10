package com.project.goms.domain.outing.usecase

import com.project.goms.global.annotation.UseCaseWithTransaction
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

@UseCaseWithTransaction
class ValidateOutingTimeUseCase {

    fun execute() : Boolean{
        val currentDate = LocalDate.now()
        val currentTime = LocalTime.now()

        if (currentDate.dayOfWeek != DayOfWeek.WEDNESDAY ||
            (currentTime.isBefore(LocalTime.of(18, 50)) || currentTime.isAfter(LocalTime.of(19, 25)))) {
            return false
        }
        return true
    }

}