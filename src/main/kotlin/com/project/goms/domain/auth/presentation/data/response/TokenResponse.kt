package com.project.goms.domain.auth.presentation.data.response

import java.time.LocalDateTime

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExp: LocalDateTime,
    val refreshTokenExp: LocalDateTime
)
