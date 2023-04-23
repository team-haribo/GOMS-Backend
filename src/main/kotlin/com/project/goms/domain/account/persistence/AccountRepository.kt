package com.project.goms.domain.account.persistence

import com.project.goms.domain.account.Account
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface AccountRepository: CrudRepository<Account, UUID> {

    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): Account

}