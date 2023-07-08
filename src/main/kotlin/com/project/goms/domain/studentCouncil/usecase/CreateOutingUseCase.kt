package com.project.goms.domain.studentCouncil.usecase

import com.project.goms.domain.studentCouncil.common.exception.NotAvailableCreateOutingException
import com.project.goms.domain.studentCouncil.common.property.OutingUUIDExpTimeProperties
import com.project.goms.domain.studentCouncil.entity.OutingUUID
import com.project.goms.domain.studentCouncil.entity.repository.OutingUUIDRepository
import com.project.goms.global.annotation.UseCaseWithTransaction
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

@UseCaseWithTransaction
class CreateOutingUseCase(
    private val outingUUIDRepository: OutingUUIDRepository,
    private val outingUUIDExpTimeProperties: OutingUUIDExpTimeProperties
) {

    fun execute(): UUID {
        val outingUUID = OutingUUID(
            outingUUID = UUID.randomUUID(),
            expiredAt = outingUUIDExpTimeProperties.expiredAt
        )
        val currentDate = LocalDate.now()
        val currentTime = LocalTime.now()
        if (currentDate.dayOfWeek != DayOfWeek.WEDNESDAY ||
            (currentTime.isBefore(LocalTime.of(18, 50)) && currentTime.isAfter(LocalTime.of(19, 25)))) {
            throw NotAvailableCreateOutingException()
        }
        return outingUUIDRepository.save(outingUUID).outingUUID
    }

}