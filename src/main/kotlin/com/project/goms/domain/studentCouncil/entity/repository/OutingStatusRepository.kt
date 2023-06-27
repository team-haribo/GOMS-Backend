package com.project.goms.domain.studentCouncil.entity.repository

import com.project.goms.domain.studentCouncil.entity.OutingStatus
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface OutingStatusRepository: CrudRepository<OutingStatus, UUID>