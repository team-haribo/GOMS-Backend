package com.project.goms.domain.studentCouncil.usecase

import com.project.goms.domain.account.common.exception.AccountNotFoundException
import com.project.goms.domain.account.entity.repository.AccountRepository
import com.project.goms.domain.outing.entity.repository.OutingRepository
import com.project.goms.global.annotation.UseCaseWithTransaction
import org.springframework.data.repository.findByIdOrNull
import java.util.*

@UseCaseWithTransaction
class DeleteOutingUseCase(
    private val accountRepository: AccountRepository,
    private val outingRepository: OutingRepository
) {

    fun execute(accountIdx: UUID) {
        val account = accountRepository.findByIdOrNull(accountIdx) ?: throw AccountNotFoundException()
        outingRepository.deleteByAccountIdx(account.idx)
    }

}