package com.project.goms.domain.auth.presentation.data.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.project.goms.domain.account.presentation.data.enums.Authority
import java.time.LocalDateTime
import java.time.ZonedDateTime

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH-mm-ss")
    val accessTokenExp: ZonedDateTime,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH-mm-ss")
    val refreshTokenExp: ZonedDateTime,
    val authority: Authority
)
