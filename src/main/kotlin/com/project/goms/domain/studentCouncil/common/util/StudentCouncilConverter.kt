package com.project.goms.domain.studentCouncil.common.util

import com.project.goms.domain.account.presentation.data.response.AccountResponse
import com.project.goms.domain.account.usecase.dto.AccountDto
import com.project.goms.domain.studentCouncil.presentation.data.request.GrantAuthorityRequest
import com.project.goms.domain.studentCouncil.presentation.data.response.AllAccountResponse
import com.project.goms.domain.studentCouncil.usecase.dto.AllAccountDto
import com.project.goms.domain.studentCouncil.usecase.dto.GrantAuthorityDto

interface StudentCouncilConverter {

    fun toDto(request: GrantAuthorityRequest): GrantAuthorityDto
    fun toAllAccountListResponse(dto: List<AllAccountDto>): List<AllAccountResponse>
    fun toAccountListResponse(dto: List<AccountDto>): List<AccountResponse>

}