package com.project.goms.domain.rate.common.util.impl

import com.project.goms.domain.rate.common.util.RateConverter
import com.project.goms.domain.rate.presentation.data.dto.RateRankDto
import com.project.goms.domain.rate.presentation.data.response.RateRankResponse
import org.springframework.stereotype.Component

@Component
class RateConverterImpl : RateConverter {

    override fun toResponse(dto: List<RateRankDto>): List<RateRankResponse> =
        dto.map {
            RateRankResponse(
                it.accountIdx,
                RateRankResponse.StudentNum(it.studentNum.grade, it.studentNum.classNum, it.studentNum.number),
                it.profileUrl
            )
        }

}