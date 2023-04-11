package com.project.goms.global.security.principal

import com.project.goms.domain.account.common.exception.AccountNotFoundException
import com.project.goms.domain.account.domain.repository.AccountRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class StudentDetailsService(
    private val accountRepository: AccountRepository
): UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails =
        accountRepository.findByIdOrNull(UUID.fromString(username))
            .let { it ?: throw AccountNotFoundException() }
            .let { StudentDetails(it.idx) }

}