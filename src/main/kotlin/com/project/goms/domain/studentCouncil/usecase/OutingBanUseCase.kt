package com.project.goms.domain.studentCouncil.usecase

import com.project.goms.domain.studentCouncil.common.property.OutingStatusExpTimeProperties
import com.project.goms.domain.studentCouncil.entity.OutingStatus
import com.project.goms.domain.studentCouncil.entity.repository.OutingStatusRepository
import com.project.goms.domain.studentCouncil.usecase.dto.OutingBanDto
import com.project.goms.global.annotation.UseCaseWithTransaction
import mu.KotlinLogging
import java.util.*

private val log = KotlinLogging.logger {}

@UseCaseWithTransaction
class OutingBanUseCase(
    private val outingStatusRepository: OutingStatusRepository,
    private val outingStatusExpTimeProperties: OutingStatusExpTimeProperties
) {

    fun execute(outingBanDto: OutingBanDto) {
        when (outingBanDto.status) {
            true -> {
                val outingStatus = OutingStatus(
                    outingStatusUUID = UUID.randomUUID(),
                    expiredAt = outingStatusExpTimeProperties.expiredAt
                )
                log.info("이번주는 외출 불가능 상태 입니다.")
                outingStatusRepository.save(outingStatus)
            }

            false -> {
                log.info("이번주는 외출 가능한 상태입니다.")
                outingStatusRepository.deleteAll()
            }
        }
    }

}