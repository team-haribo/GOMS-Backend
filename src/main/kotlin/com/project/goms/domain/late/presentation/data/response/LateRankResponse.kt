package com.project.goms.domain.late.presentation.data.response

import java.util.*

data class LateRankResponse(
    val accountIdx: UUID,
    val name: String,
    val studentNum: StudentNum,
    val profileUrl: String?,
) {
    data class StudentNum(
        val grade: Int,
        val classNum: Int,
        val number: Int
    )
}