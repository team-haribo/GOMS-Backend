package com.project.goms.domain.late.entity.repository

import com.project.goms.domain.late.entity.Late
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.time.LocalDate
import java.util.UUID

interface LateRepository: CrudRepository<Late, UUID> {

    fun countByAccountIdx(accountIdx: UUID): Long
    @Query("select count(*) from late l where l.createdTime = :oneWeekAgo")
    fun lateCountOntWeekAgo(oneWeekAgo: LocalDate): Long

}