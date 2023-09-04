package com.project.goms.domain.auth.presentation.data.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.project.goms.domain.account.entity.Authority
import java.time.LocalDateTime

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val accessTokenExp: LocalDateTime,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val refreshTokenExp: LocalDateTime,
    val authority: Authority
)
