package com.project.goms.domain.outing.persistence.repository

import com.project.goms.domain.account.persistence.entity.Account
import com.project.goms.domain.outing.persistence.entity.Outing
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface OutingRepository: CrudRepository<Outing, UUID> {

    fun existsByAccount(account: Account): Boolean
    fun queryAllByOrderByCreatedTimeDesc(): List<Outing>
    fun deleteByAccountIdx(accountIdx: UUID)

}