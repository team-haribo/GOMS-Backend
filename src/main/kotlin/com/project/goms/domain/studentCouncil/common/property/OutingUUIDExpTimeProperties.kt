package com.project.goms.domain.studentCouncil.common.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("outing")
class OutingUUIDExpTimeProperties(
    val expiredAt: Int
)