package com.project.goms.global.security.jwt

import com.project.goms.domain.account.entity.Authority
import com.project.goms.domain.auth.entity.RefreshToken
import com.project.goms.domain.auth.entity.repository.RefreshTokenRepository
import com.project.goms.domain.auth.usecase.dto.TokenDto
import com.project.goms.global.security.jwt.common.properties.JwtExpTimeProperties
import com.project.goms.global.security.jwt.common.properties.JwtProperties
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class JwtGenerator(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val jwtProperties: JwtProperties,
    private val jwtExpTimeProperties: JwtExpTimeProperties
) {

    fun generateToken(accountIdx: UUID, authority: Authority): TokenDto =
        TokenDto(
            accessToken = generateAccessToken(accountIdx, authority),
            refreshToken = generateRefreshToken(accountIdx),
            accessTokenExp = LocalDateTime.now().plusSeconds(jwtExpTimeProperties.accessExp.toLong()),
            refreshTokenExp = LocalDateTime.now().plusSeconds(jwtExpTimeProperties.refreshExp),
            authority = authority
        )

    private fun generateAccessToken(accountIdx: UUID, authority: Authority): String =
        Jwts.builder()
            .signWith(jwtProperties.accessSecret, SignatureAlgorithm.HS256)
            .setSubject(accountIdx.toString())
            .claim(JwtProperties.TOKEN_TYPE, JwtProperties.ACCESS)
            .claim(JwtProperties.AUTHORITY, authority)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtExpTimeProperties.accessExp * 1000))
            .compact()

    private fun generateRefreshToken(accountIdx: UUID): String {
        val refreshToken = Jwts.builder()
            .signWith(jwtProperties.refreshSecret, SignatureAlgorithm.HS256)
            .setSubject(accountIdx.toString())
            .claim(JwtProperties.TOKEN_TYPE, JwtProperties.REFRESH)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtExpTimeProperties.refreshExp))
            .compact()

        refreshTokenRepository.save(RefreshToken(refreshToken, accountIdx, jwtExpTimeProperties.refreshExp))
        return refreshToken
    }

}