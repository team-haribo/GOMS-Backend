package com.project.goms.domain.account.common.util.impl

import com.project.goms.domain.account.common.exception.AccountNotFoundException
import com.project.goms.domain.account.common.util.AccountUtil
import com.project.goms.domain.account.persistence.entity.Account
import com.project.goms.domain.account.persistence.repository.AccountRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class AccountUtilImpl(
    private val accountRepository: AccountRepository
): AccountUtil {

    override fun getCurrentAccount(): Account {
        val accountIdx = UUID.fromString(SecurityContextHolder.getContext().authentication.name)
        return accountRepository.findByIdOrNull(accountIdx) ?: throw AccountNotFoundException()
    }

}