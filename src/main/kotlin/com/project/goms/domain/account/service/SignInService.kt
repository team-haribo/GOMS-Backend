package com.project.goms.domain.account.service

import com.project.goms.domain.account.presentation.data.dto.SignInDto
import com.project.goms.domain.account.presentation.data.dto.TokenDto
import java.util.*

interface SignInService {

    fun execute(dto: SignInDto): TokenDto

}