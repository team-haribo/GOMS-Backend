package com.project.goms.domain.auth.presentation.data.dto

import java.time.LocalDateTime

data class TokenDto(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExp: LocalDateTime,
    val refreshTokenExp: LocalDateTime
)