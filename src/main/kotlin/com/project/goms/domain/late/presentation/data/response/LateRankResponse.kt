package com.project.goms.domain.late.presentation.data.response

import com.project.goms.domain.account.presentation.data.response.StudentNumResponse
import java.util.*

data class LateRankResponse(
    val accountIdx: UUID,
    val name: String,
    val studentNum: StudentNumResponse,
    val profileUrl: String?,
)