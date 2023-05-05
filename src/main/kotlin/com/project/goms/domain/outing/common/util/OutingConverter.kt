package com.project.goms.domain.outing.common.util

import com.project.goms.domain.account.entity.Account
import com.project.goms.domain.outing.entity.Outing
import com.project.goms.domain.outing.usecase.dto.OutingAccountDto
import com.project.goms.domain.outing.presentation.data.response.OutingAccountResponse
import com.project.goms.domain.outing.presentation.data.response.OutingCountResponse

interface OutingConverter {

    fun toEntity(account: Account): Outing
    fun toResponse(dto: List<OutingAccountDto>): List<OutingAccountResponse>
    fun toResponse(outingCount: Long): OutingCountResponse

}