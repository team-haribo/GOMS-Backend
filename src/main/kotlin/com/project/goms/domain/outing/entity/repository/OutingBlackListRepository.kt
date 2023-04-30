package com.project.goms.domain.outing.entity.repository

import com.project.goms.domain.outing.entity.OutingBlackList
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface OutingBlackListRepository: CrudRepository<OutingBlackList, UUID>