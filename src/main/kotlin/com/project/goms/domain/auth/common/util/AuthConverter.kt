package com.project.goms.domain.auth.common.util

import com.project.goms.domain.auth.usecase.dto.SignInDto
import com.project.goms.domain.auth.usecase.dto.TokenDto
import com.project.goms.domain.auth.presentation.data.request.SignInRequest
import com.project.goms.domain.auth.presentation.data.response.TokenResponse

interface AuthConverter {

    fun toDto(request: SignInRequest): SignInDto
    fun toResponse(dto: TokenDto): TokenResponse

}