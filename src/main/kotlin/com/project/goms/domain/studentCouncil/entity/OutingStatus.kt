package com.project.goms.domain.studentCouncil.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import java.util.*
import java.util.concurrent.TimeUnit

@RedisHash("outing_status")
data class OutingStatus (
    @Id
    val outingStatusUUID: UUID,

    @TimeToLive(unit = TimeUnit.SECONDS)
    val expiredAt: Int
)