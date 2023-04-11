package com.project.goms.domain.account.common.util

import com.project.goms.domain.account.presentation.data.dto.SignInDto
import com.project.goms.domain.account.presentation.data.dto.TokenDto
import com.project.goms.domain.account.presentation.data.request.SignInRequest
import com.project.goms.domain.account.presentation.data.response.TokenResponse

interface AccountConverter {

    fun toDto(request: SignInRequest): SignInDto
    fun toResponse(dto: TokenDto): TokenResponse

}