package com.project.goms.domain.auth.common.util.impl

import com.project.goms.domain.auth.common.util.AuthConverter
import com.project.goms.domain.auth.usecase.dto.SignInDto
import com.project.goms.domain.auth.usecase.dto.TokenDto
import com.project.goms.domain.auth.presentation.data.request.SignInRequest
import com.project.goms.domain.auth.presentation.data.response.TokenResponse
import org.springframework.stereotype.Component

@Component
class AuthConverterImpl: AuthConverter {

    override fun toDto(request: SignInRequest): SignInDto =
        SignInDto(code = request.code)

    override fun toResponse(dto: TokenDto): TokenResponse =
        TokenResponse(
            accessToken = dto.accessToken,
            refreshToken = dto.refreshToken,
            accessTokenExp = dto.accessTokenExp,
            refreshTokenExp = dto.refreshTokenExp,
            authority = dto.authority
        )

}