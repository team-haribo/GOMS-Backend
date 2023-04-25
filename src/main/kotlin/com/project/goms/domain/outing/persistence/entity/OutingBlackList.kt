package com.project.goms.domain.outing.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import java.util.UUID
import java.util.concurrent.TimeUnit

@RedisHash
data class OutingBlackList(
    @Id
    val accountIdx: UUID,

    @TimeToLive(unit = TimeUnit.SECONDS)
    val blackListTime: Int
)