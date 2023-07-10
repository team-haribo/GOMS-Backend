package com.project.goms.domain.account.usecase.dto

import com.project.goms.domain.account.entity.Authority
import java.util.*

data class ProfileDto(
    val accountIdx: UUID,
    val name: String,
    val studentNum: StudentNumberDto,
    val authority: Authority,
    val profileUrl: String?,
    val lateCount: Long,
    val isOuting: Boolean,
    val isBlackList: Boolean
)