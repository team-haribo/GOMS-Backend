package com.project.goms.domain.outing.usecase

import com.project.goms.domain.account.common.util.AccountUtil
import com.project.goms.domain.outing.common.exception.BlackListNotAllowOutingException
import com.project.goms.domain.outing.common.exception.NotAvailableOutingTimeException
import com.project.goms.domain.outing.common.exception.OutingUUIDUnverifiedException
import com.project.goms.domain.outing.common.util.OutingConverter
import com.project.goms.domain.outing.entity.repository.OutingBlackListRepository
import com.project.goms.domain.outing.entity.repository.OutingRepository
import com.project.goms.domain.studentCouncil.entity.repository.OutingUUIDRepository
import com.project.goms.global.annotation.UseCaseWithTransaction
import java.time.LocalTime
import java.util.UUID

@UseCaseWithTransaction
class OutingUseCase(
    private val outingRepository: OutingRepository,
    private val outingBlackListRepository: OutingBlackListRepository,
    private val outingUUIDRepository: OutingUUIDRepository,
    private val outingConverter: OutingConverter,
    private val accountUtil: AccountUtil,
) {

    fun execute(outingUUID: UUID) {
        val account = accountUtil.getCurrentAccount()
        val outing = outingConverter.toEntity(account)
        val currentTime = LocalTime.now()

        if (currentTime.isAfter(LocalTime.of(18, 50)) && currentTime.isBefore(LocalTime.of(19, 25))) {

            if (outingBlackListRepository.existsById(account.idx)) {
                throw BlackListNotAllowOutingException()
            }

            if (!outingUUIDRepository.existsById(outingUUID)) {
                throw OutingUUIDUnverifiedException()
            }

            when (outingRepository.existsByAccount(account)) {
                false -> outingRepository.save(outing)
                true -> outingRepository.deleteByAccountIdx(account.idx)
            }

        } else {
            throw NotAvailableOutingTimeException()
        }

    }

}