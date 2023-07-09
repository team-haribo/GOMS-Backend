package com.project.goms.domain.studentCouncil.common.util

import com.project.goms.domain.studentCouncil.presentation.data.request.GrantAuthorityRequest
import com.project.goms.domain.studentCouncil.presentation.data.response.AllAccountResponse
import com.project.goms.domain.studentCouncil.usecase.dto.AllAccountDto
import com.project.goms.domain.studentCouncil.usecase.dto.GrantAuthorityDto

interface StudentCouncilConverter {

    fun toDto(request: GrantAuthorityRequest): GrantAuthorityDto
    fun toResponse(dto: List<AllAccountDto>): List<AllAccountResponse>

}