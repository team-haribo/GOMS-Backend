package com.project.goms.domain.outing.presentation.data.dto

import java.util.*

data class OutingAccountDto(
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