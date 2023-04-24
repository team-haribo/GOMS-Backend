package com.project.goms.domain.rate.presentation.data.dto

import java.util.*

data class RateRankDto(
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