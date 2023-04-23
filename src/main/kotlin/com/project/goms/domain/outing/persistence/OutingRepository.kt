package com.project.goms.domain.outing.persistence

import com.project.goms.domain.outing.Outing
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface OutingRepository: CrudRepository<Outing, UUID> {
}