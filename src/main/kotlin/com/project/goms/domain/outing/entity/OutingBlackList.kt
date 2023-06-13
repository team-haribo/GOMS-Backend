package com.project.goms.domain.outing.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed
import java.util.*
import java.util.concurrent.TimeUnit

@RedisHash("outing_black_list")
data class OutingBlackList(
    @Id
    val accountIdx: UUID,

    @TimeToLive(unit = TimeUnit.SECONDS)
    val expiredAt: Int
)