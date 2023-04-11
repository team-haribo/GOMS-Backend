package com.project.goms.domain.account.common.util.impl

import com.project.goms.domain.account.common.util.AccountConverter
import com.project.goms.domain.account.presentation.data.dto.SignInDto
import com.project.goms.domain.account.presentation.data.dto.TokenDto
import com.project.goms.domain.account.presentation.data.request.SignInRequest
import com.project.goms.domain.account.presentation.data.response.TokenResponse
import org.springframework.stereotype.Component

@Component
class AccountConverterImpl : AccountConverter {

    override fun toDto(request: SignInRequest): SignInDto =
        SignInDto(code = request.code)

    override fun toResponse(dto: TokenDto): TokenResponse =
        TokenResponse(
            accessToken = dto.accessToken,
            refreshToken = dto.refreshToken,
            accessTokenExp = dto.accessTokenExp,
            refreshTokenExp = dto.refreshTokenExp
        )

}