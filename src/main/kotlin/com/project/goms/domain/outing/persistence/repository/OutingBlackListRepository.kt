package com.project.goms.domain.outing.persistence.repository

import com.project.goms.domain.outing.persistence.entity.OutingBlackList
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface OutingBlackListRepository: CrudRepository<OutingBlackList, UUID>