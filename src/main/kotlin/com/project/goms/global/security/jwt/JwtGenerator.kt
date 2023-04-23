package com.project.goms.global.security.jwt

import com.project.goms.domain.auth.RefreshToken
import com.project.goms.domain.auth.persistence.RefreshTokenRepository
import com.project.goms.domain.auth.presentation.data.dto.TokenDto
import com.project.goms.domain.auth.presentation.data.enums.Authority
import com.project.goms.global.security.jwt.common.properties.JwtExpTimeProperties
import com.project.goms.global.security.jwt.common.properties.JwtProperties
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.Date
import java.util.UUID

@Component
class JwtGenerator(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val jwtProperties: JwtProperties,
    private val jwtExpTimeProperties: JwtExpTimeProperties
) {

    fun generateToken(sub: UUID, authority: Authority): TokenDto =
        TokenDto(
            accessToken = generateAccessToken(sub, authority),
            refreshToken = generateRefreshToken(sub, authority),
            accessTokenExp = LocalDateTime.now().plusSeconds(jwtExpTimeProperties.accessExp.toLong()),
            refreshTokenExp = LocalDateTime.now().plusSeconds(jwtExpTimeProperties.refreshExp.toLong())
        )

    private fun generateAccessToken(sub: UUID, authority: Authority): String =
        Jwts.builder()
            .signWith(jwtProperties.accessSecret, SignatureAlgorithm.HS256)
            .setSubject(sub.toString())
            .claim(JwtProperties.TOKEN_TYPE, JwtProperties.ACCESS)
            .claim(JwtProperties.AUTHORITY, authority)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtExpTimeProperties.accessExp * 1000))
            .compact()

    private fun generateRefreshToken(sub: UUID, authority: Authority): String {
        val refreshToken = Jwts.builder()
            .signWith(jwtProperties.accessSecret, SignatureAlgorithm.HS256)
            .setSubject(sub.toString())
            .claim(JwtProperties.TOKEN_TYPE, JwtProperties.REFRESH)
            .claim(JwtProperties.AUTHORITY, authority)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtExpTimeProperties.accessExp * 1000))
            .compact()

        refreshTokenRepository.save(RefreshToken(refreshToken, sub, jwtExpTimeProperties.refreshExp))
        return refreshToken
    }

}