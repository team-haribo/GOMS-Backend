package com.project.goms.domain.studentCouncil.usecase

import com.project.goms.domain.studentCouncil.common.property.OutingUUIDExpTimeProperties
import com.project.goms.domain.studentCouncil.entity.OutingUUID
import com.project.goms.domain.studentCouncil.entity.repository.OutingUUIDRepository
import com.project.goms.global.annotation.UseCaseWithTransaction
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

        return outingUUIDRepository.save(outingUUID).outingUUID
    }

}