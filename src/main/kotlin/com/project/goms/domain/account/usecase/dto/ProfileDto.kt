package com.project.goms.domain.account.usecase.dto

import java.util.*

data class ProfileDto(
    val accountIdx: UUID,
    val name: String,
    val studentNum: StudentNumberDto,
    val profileUrl: String?,
    val lateCount: Long,
    val isOuting: Boolean,
    val isBlackList: Boolean
)