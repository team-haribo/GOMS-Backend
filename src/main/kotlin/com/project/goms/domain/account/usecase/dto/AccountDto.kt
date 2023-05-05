package com.project.goms.domain.account.usecase.dto

import com.project.goms.domain.account.entity.Authority
import java.util.*

data class AccountDto(
    val accountIdx: UUID,
    val name: String,
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