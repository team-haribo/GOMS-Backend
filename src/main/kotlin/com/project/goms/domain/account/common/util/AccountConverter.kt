package com.project.goms.domain.account.common.util

import com.project.goms.domain.account.presentation.data.dto.AccountDto
import com.project.goms.domain.account.presentation.data.dto.GrantAuthorityDto
import com.project.goms.domain.account.presentation.data.dto.ProfileDto
import com.project.goms.domain.account.presentation.data.request.GrantAuthorityRequest
import com.project.goms.domain.account.presentation.data.response.AccountResponse
import com.project.goms.domain.account.presentation.data.response.ProfileResponse

interface AccountConverter {

    fun toDto(request: GrantAuthorityRequest): GrantAuthorityDto
    fun toResponse(dto: ProfileDto): ProfileResponse
    fun toResponse(dto: List<AccountDto>): List<AccountResponse>

}