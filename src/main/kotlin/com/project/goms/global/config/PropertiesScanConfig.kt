package com.project.goms.global.config

import com.project.goms.domain.studentCouncil.common.property.OutingUUIDExpTimeProperties
import com.project.goms.global.gauth.property.GAuthProperties
import com.project.goms.global.security.jwt.common.properties.JwtExpTimeProperties
import com.project.goms.global.security.jwt.common.properties.JwtProperties
import com.project.goms.infrastructure.notification.property.FcmProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan(
    basePackageClasses = [
        JwtProperties::class,
        JwtExpTimeProperties::class,
        GAuthProperties::class,
        OutingUUIDExpTimeProperties::class,
        FcmProperties::class
    ]
)
class PropertiesScanConfig