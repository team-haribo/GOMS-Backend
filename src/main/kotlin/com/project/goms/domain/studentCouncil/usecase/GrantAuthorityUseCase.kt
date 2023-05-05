package com.project.goms.domain.studentCouncil.usecase

import com.project.goms.domain.account.common.exception.AccountNotFoundException
import com.project.goms.domain.account.entity.repository.AccountRepository
import com.project.goms.domain.account.entity.updateAuthority
import com.project.goms.domain.studentCouncil.usecase.dto.GrantAuthorityDto
import com.project.goms.global.annotation.UseCaseWithTransaction
import org.springframework.data.repository.findByIdOrNull

@UseCaseWithTransaction
class GrantAuthorityUseCase(
    private val accountRepository: AccountRepository
) {

    fun execute(dto: GrantAuthorityDto) {
        val account = accountRepository.findByIdOrNull(dto.accountIdx)
            ?: throw AccountNotFoundException()
        account.updateAuthority(dto.authority)
    }

}