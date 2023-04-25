package com.project.goms.domain.outing.usecase

import com.project.goms.domain.account.common.util.AccountUtil
import com.project.goms.domain.outing.common.exception.BlackListNotAllowOutingException
import com.project.goms.domain.outing.common.util.OutingConverter
import com.project.goms.domain.outing.persistence.repository.OutingBlackListRepository
import com.project.goms.domain.outing.persistence.repository.OutingRepository
import com.project.goms.global.annotation.UseCaseWithTransaction

@UseCaseWithTransaction
class OutingUseCase(
    private val outingRepository: OutingRepository,
    private val outingBlackListRepository: OutingBlackListRepository,
    private val outingConverter: OutingConverter,
    private val accountUtil: AccountUtil,
) {

    fun execute() {
        val account = accountUtil.getCurrentAccount()
        val outing = outingConverter.toEntity(account)

        if (outingBlackListRepository.existsById(account.idx)) {
            throw BlackListNotAllowOutingException()
        }

        when (outingRepository.existsByAccount(account)) {
            false -> outingRepository.save(outing)
            true -> outingRepository.deleteByAccountIdx(account.idx)
        }
    }

}