package com.project.goms.domain.account.presentation.data.dto

import com.project.goms.domain.account.presentation.data.enums.Authority
import java.util.UUID

data class GrantAuthorityDto(
    val accountIdx: UUID,
    val authority: Authority
)