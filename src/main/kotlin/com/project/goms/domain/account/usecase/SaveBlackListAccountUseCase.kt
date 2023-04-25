package com.project.goms.domain.account.usecase

import com.project.goms.domain.account.common.exception.AccountNotFoundException
import com.project.goms.domain.account.persistence.repository.AccountRepository
import com.project.goms.domain.outing.persistence.entity.OutingBlackList
import com.project.goms.domain.outing.persistence.repository.OutingBlackListRepository
import com.project.goms.global.annotation.UseCaseWithTransaction
import org.springframework.data.repository.findByIdOrNull
import java.util.UUID

@UseCaseWithTransaction
class SaveBlackListAccountUseCase(
    private val accountRepository: AccountRepository,
    private val outingBlackListRepository: OutingBlackListRepository
) {

    fun execute(accountIdx: UUID) {
        val account = accountRepository.findByIdOrNull(accountIdx) ?: throw AccountNotFoundException()
        val outingBlackList = OutingBlackList(accountIdx = account.idx, blackListTime = 604800)
        outingBlackListRepository.save(outingBlackList)
    }

}