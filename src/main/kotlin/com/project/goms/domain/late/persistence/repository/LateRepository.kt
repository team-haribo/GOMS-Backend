package com.project.goms.domain.late.persistence.repository

import com.project.goms.domain.late.persistence.entity.Late
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface LateRepository: CrudRepository<Late, UUID> {

    fun countByAccountIdx(accountIdx: UUID): Long
    fun findTop5ByOrderByAccountDesc(): List<Late>

}