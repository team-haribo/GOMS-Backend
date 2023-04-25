package com.project.goms.domain.late.common.util

import com.project.goms.domain.late.presentation.data.dto.LateRankDto
import com.project.goms.domain.late.presentation.data.response.LateRankResponse

interface LateConverter {

    fun toResponse(dto: List<LateRankDto>): List<LateRankResponse>

}