package com.project.goms.domain.account.presentation.data.request

import com.project.goms.domain.account.presentation.data.enums.Authority
import java.util.UUID

data class GrantAuthorityRequest(
    val accountIdx: UUID,
    val authority: Authority
)