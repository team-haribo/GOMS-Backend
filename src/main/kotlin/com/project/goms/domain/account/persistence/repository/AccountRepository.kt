package com.project.goms.domain.account.persistence.repository

import com.project.goms.domain.account.persistence.entity.Account
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface AccountRepository: CrudRepository<Account, UUID> {

    fun findByEmail(email: String): Account?
    fun findAllByOrderByGradeAscClassNumAscNumberAsc(): List<Account>

}