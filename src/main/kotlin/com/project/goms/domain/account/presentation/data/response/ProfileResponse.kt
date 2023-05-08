package com.project.goms.domain.account.presentation.data.response

import java.util.UUID

data class ProfileResponse(
    val accountIdx: UUID,
    val name: String,
    val studentNum: StudentNumResponse,
    val profileUrl: String?,
    val lateCount: Long
)
