package com.project.goms.domain.late.common.util.impl

import com.project.goms.domain.late.common.util.LateConverter
import com.project.goms.domain.late.presentation.data.dto.LateRankDto
import com.project.goms.domain.late.presentation.data.response.LateRankResponse
import org.springframework.stereotype.Component

@Component
class LateConverterImpl : LateConverter {

    override fun toResponse(dto: List<LateRankDto>): List<LateRankResponse> =
        dto.map {
            LateRankResponse(
                accountIdx = it.accountIdx,
                name = it.name,
                studentNum = LateRankResponse.StudentNum(it.studentNum.grade, it.studentNum.classNum, it.studentNum.number),
                profileUrl = it.profileUrl
            )
        }

}