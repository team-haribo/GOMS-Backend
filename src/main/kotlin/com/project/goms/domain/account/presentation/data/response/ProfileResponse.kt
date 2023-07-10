package com.project.goms.domain.account.presentation.data.response

import com.project.goms.domain.account.entity.Authority
import java.util.UUID

data class ProfileResponse(
    val accountIdx: UUID,
    val name: String,
    val studentNum: StudentNumResponse,
    val authority: Authority,
    val profileUrl: String?,
    val lateCount: Long,
    val isOuting: Boolean,
    val isBlackList: Boolean
)
