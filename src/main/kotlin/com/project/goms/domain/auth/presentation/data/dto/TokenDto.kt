package com.project.goms.domain.auth.presentation.data.dto

import com.project.goms.domain.account.presentation.data.enums.Authority
import java.time.ZonedDateTime

data class TokenDto(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExp: ZonedDateTime,
    val refreshTokenExp: ZonedDateTime,
    val authority: Authority
)