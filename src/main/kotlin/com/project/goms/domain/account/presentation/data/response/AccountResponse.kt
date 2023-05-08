package com.project.goms.domain.account.presentation.data.response

import com.project.goms.domain.account.entity.Authority
import java.util.*

data class AccountResponse(
    val accountIdx: UUID,
    val name: String,
    val studentNum: StudentNumResponse,
    val profileUrl: String?,
    val authority: Authority
)