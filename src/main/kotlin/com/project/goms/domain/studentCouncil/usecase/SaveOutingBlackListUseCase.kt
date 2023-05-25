package com.project.goms.domain.studentCouncil.usecase

import com.project.goms.domain.account.common.exception.AccountNotFoundException
import com.project.goms.domain.account.entity.repository.AccountRepository
import com.project.goms.domain.outing.entity.OutingBlackList
import com.project.goms.domain.outing.entity.repository.OutingBlackListRepository
import com.project.goms.global.annotation.UseCaseWithTransaction
import org.springframework.data.repository.findByIdOrNull
import java.util.UUID

@UseCaseWithTransaction
class SaveOutingBlackListUseCase(
    private val accountRepository: AccountRepository,
    private val outingBlackListRepository: OutingBlackListRepository
) {

    fun execute(accountIdx: UUID) {
        val account = accountRepository.findByIdOrNull(accountIdx) ?: throw AccountNotFoundException()
        val outingBlackList = OutingBlackList(accountIdx = account.idx, expiredAt = 604800)
        outingBlackListRepository.save(outingBlackList)
    }

}