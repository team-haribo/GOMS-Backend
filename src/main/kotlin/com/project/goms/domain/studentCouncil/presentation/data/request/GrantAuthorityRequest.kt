package com.project.goms.domain.studentCouncil.presentation.data.request

import com.project.goms.domain.account.entity.Authority
import org.jetbrains.annotations.NotNull
import java.util.UUID

data class GrantAuthorityRequest(
    @field:NotNull
    val accountIdx: UUID,
    @field:NotNull
    val authority: Authority
)