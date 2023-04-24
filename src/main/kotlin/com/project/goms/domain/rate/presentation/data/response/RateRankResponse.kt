package com.project.goms.domain.rate.presentation.data.response

import java.util.*

data class RateRankResponse(
    val accountIdx: UUID,
    val studentNum: StudentNum,
    val profileUrl: String?,
) {
    data class StudentNum(
        val grade: Int,
        val classNum: Int,
        val number: Int
    )
}