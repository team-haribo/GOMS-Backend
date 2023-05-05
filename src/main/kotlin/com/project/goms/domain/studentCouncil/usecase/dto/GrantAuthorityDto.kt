package com.project.goms.domain.studentCouncil.usecase.dto

import com.project.goms.domain.account.entity.Authority
import java.util.UUID

data class GrantAuthorityDto(
    val accountIdx: UUID,
    val authority: Authority
)