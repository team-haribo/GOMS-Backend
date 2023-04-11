package com.project.goms.global.gauth

import gauth.GAuth
import gauth.impl.GAuthImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GauthConfiguration {

    @Bean
    fun gauth(): GAuth = GAuthImpl()

}