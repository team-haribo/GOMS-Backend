package com.project.goms.domain.studentCouncil.presentation.data.request

import com.project.goms.domain.account.entity.Authority
import java.util.UUID

data class GrantAuthorityRequest(
    val accountIdx: UUID,
    val authority: Authority
)