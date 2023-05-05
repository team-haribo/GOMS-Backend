package com.project.goms.domain.studentCouncil.common.util

import com.project.goms.domain.account.presentation.data.dto.AccountDto
import com.project.goms.domain.account.presentation.data.response.AccountResponse
import com.project.goms.domain.studentCouncil.presentation.data.dto.GrantAuthorityDto
import com.project.goms.domain.studentCouncil.presentation.data.request.GrantAuthorityRequest

interface StudentCouncilConverter {

    fun toDto(request: GrantAuthorityRequest): GrantAuthorityDto
    fun toResponse(dto: List<AccountDto>): List<AccountResponse>

}