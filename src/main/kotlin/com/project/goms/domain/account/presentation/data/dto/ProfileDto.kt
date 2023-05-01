package com.project.goms.domain.account.presentation.data.dto

import java.util.*

data class ProfileDto(
    val accountIdx: UUID,
    val name: String,
    val studentNum: StudentNum,
    val profileUrl: String?,
    val lateCount: Long
) {
    data class StudentNum(
        val grade: Int,
        val classNum: Int,
        val number: Int
    )
}