package com.project.goms.domain.account.usecase.dto

import com.project.goms.domain.account.entity.Authority
import java.util.*

data class AccountDto(
    val accountIdx: UUID,
    val name: String,
    val studentNum: StudentNumberDto,
    val profileUrl: String?,
    val authority: Authority
)