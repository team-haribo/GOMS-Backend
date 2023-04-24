package com.project.goms.domain.rate.common.util

import com.project.goms.domain.rate.presentation.data.dto.RateRankDto
import com.project.goms.domain.rate.presentation.data.response.RateRankResponse

interface RateConverter {

    fun toResponse(dto: List<RateRankDto>): List<RateRankResponse>

}