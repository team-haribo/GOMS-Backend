package com.project.goms.domain.account.presentation.data.response

import com.project.goms.domain.account.presentation.data.enums.Authority
import java.util.*

data class AccountResponse(
    val accountIdx: UUID,
    val studentNum: StudentNum,
    val profileUrl: String?,
    val authority: Authority
) {
    data class StudentNum(
        val grade: Int,
        val classNum: Int,
        val number: Int
    )
}