package com.project.goms.domain.account.usecase

import com.project.goms.domain.account.common.exception.AccountNotFoundException
import com.project.goms.domain.account.persistence.entity.updateAuthority
import com.project.goms.domain.account.persistence.repository.AccountRepository
import com.project.goms.domain.account.presentation.data.dto.GrantAuthorityDto
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
        accountRepository.save(account)
    }

}