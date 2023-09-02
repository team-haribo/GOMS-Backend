package com.project.goms.domain.notification.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.util.UUID

@RedisHash
data class DeviceToken(
    @Id
    val accountIdx: UUID,
    val token: String
)