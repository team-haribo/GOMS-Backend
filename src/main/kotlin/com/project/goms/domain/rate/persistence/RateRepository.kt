package com.project.goms.domain.rate.persistence

import com.project.goms.domain.rate.Rate
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface RateRepository: CrudRepository<Rate, UUID> {

    fun countByAccountIdx(accountIdx: UUID): Int
    fun findTop3ByOrderByAccountDesc(): List<Rate>

}