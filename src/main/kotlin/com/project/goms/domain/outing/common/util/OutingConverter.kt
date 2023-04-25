package com.project.goms.domain.outing.common.util

import com.project.goms.domain.account.persistence.entity.Account
import com.project.goms.domain.outing.persistence.entity.Outing
import com.project.goms.domain.outing.presentation.data.dto.OutingAccountDto
import com.project.goms.domain.outing.presentation.data.response.OutingAccountResponse
import com.project.goms.domain.outing.presentation.data.response.OutingCountResponse

interface OutingConverter {

    fun toEntity(account: Account): Outing
    fun toResponse(dto: List<OutingAccountDto>): List<OutingAccountResponse>
    fun toResponse(outingCount: Long): OutingCountResponse

}