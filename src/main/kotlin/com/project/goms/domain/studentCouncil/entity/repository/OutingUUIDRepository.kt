package com.project.goms.domain.studentCouncil.entity.repository

import com.project.goms.domain.studentCouncil.entity.OutingUUID
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface OutingUUIDRepository: CrudRepository<OutingUUID, UUID>