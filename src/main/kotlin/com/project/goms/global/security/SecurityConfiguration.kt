package com.project.goms.global.security

import com.project.goms.domain.account.presentation.data.enums.Authority
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
            // /auth
            .mvcMatchers(HttpMethod.POST, "/api/v1/auth/signin").permitAll()
            .mvcMatchers(HttpMethod.PATCH, "/api/v1/auth").permitAll()

            // /account
            .mvcMatchers(HttpMethod.GET, "/api/v1/account/profile").hasAnyAuthority(Authority.ROLE_STUDENT.name, Authority.ROLE_ADMIN.name)

            // /outing
            .mvcMatchers(HttpMethod.POST, "/api/v1/outing").hasAnyAuthority(Authority.ROLE_STUDENT.name)
            .mvcMatchers(HttpMethod.GET, "/api/v1/outing").hasAnyAuthority(Authority.ROLE_STUDENT.name, Authority.ROLE_ADMIN.name)
            .mvcMatchers(HttpMethod.GET, "/api/v1/outing/count").hasAnyAuthority(Authority.ROLE_STUDENT.name, Authority.ROLE_ADMIN.name)

            // /late
            .mvcMatchers(HttpMethod.GET, "/api/v1/late/rank").hasAnyAuthority(Authority.ROLE_STUDENT.name, Authority.ROLE_ADMIN.name)

            // /admin
            .mvcMatchers(HttpMethod.GET, "/api/v1/admin/account").hasAnyAuthority(Authority.ROLE_ADMIN.name)
            .mvcMatchers(HttpMethod.PATCH, "/api/v1/admin/authority").hasAnyAuthority(Authority.ROLE_ADMIN.name)
            .mvcMatchers(HttpMethod.POST, "/api/v1/admin/black-list/{accountIdx}").hasAnyAuthority(Authority.ROLE_ADMIN.name)
            .mvcMatchers(HttpMethod.GET, "/api/v1/admin/search").hasAnyAuthority(Authority.ROLE_ADMIN.name)

            .mvcMatchers(HttpMethod.GET, "/").permitAll()
            .anyRequest().denyAll()
            .and()

            .exceptionHandling()
            .authenticationEntryPoint(CustomAuthenticationEntryPoint())
            .and()

            .apply(FilterConfig(jwtParser))
            .and()
            .build()

}