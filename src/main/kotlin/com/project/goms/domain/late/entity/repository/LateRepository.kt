package com.project.goms.domain.late.entity.repository

import com.project.goms.domain.late.entity.Late
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface LateRepository: CrudRepository<Late, UUID> {

    fun countByAccountIdx(accountIdx: UUID): Long

}