package com.project.goms.global.security

import com.project.goms.global.filter.config.FilterConfig
import com.project.goms.global.security.handler.CustomAuthenticationEntryPoint
import com.project.goms.global.security.jwt.JwtParser
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val jwtParser: JwtParser
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .cors()
            .and()
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()

            .authorizeRequests()
            .mvcMatchers(HttpMethod.POST, "/api/v1/auth/signin").permitAll()
            .anyRequest().denyAll()
            .and()

            .exceptionHandling()
            .authenticationEntryPoint(CustomAuthenticationEntryPoint())
            .and()

            .apply(FilterConfig(jwtParser))
            .and()
            .build()

}