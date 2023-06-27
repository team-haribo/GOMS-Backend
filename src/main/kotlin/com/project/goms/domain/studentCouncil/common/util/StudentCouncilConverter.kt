package com.project.goms.domain.studentCouncil.common.util

import com.project.goms.domain.studentCouncil.presentation.data.request.GrantAuthorityRequest
import com.project.goms.domain.studentCouncil.presentation.data.request.OutingBanRequest
import com.project.goms.domain.studentCouncil.presentation.data.response.AllAccountResponse
import com.project.goms.domain.studentCouncil.usecase.dto.AllAccountDto
import com.project.goms.domain.studentCouncil.usecase.dto.GrantAuthorityDto
import com.project.goms.domain.studentCouncil.usecase.dto.OutingBanDto

interface StudentCouncilConverter {

    fun toDto(request: GrantAuthorityRequest): GrantAuthorityDto
    fun toDto(request: OutingBanRequest): OutingBanDto
    fun toResponse(dto: List<AllAccountDto>): List<AllAccountResponse>

}