package com.project.goms.domain.notification.entity.repository

import com.project.goms.domain.notification.entity.DeviceToken
import org.springframework.data.repository.CrudRepository
import java.util.*

interface DeviceTokenRepository: CrudRepository<DeviceToken, UUID>