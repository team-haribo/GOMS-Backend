package com.project.goms.domain.late.presentation.data.dto

import java.util.*

data class LateRankDto(
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