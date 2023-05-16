package com.project.goms.domain.studentCouncil.usecase.dto

import com.project.goms.domain.account.entity.Authority
import com.project.goms.domain.account.usecase.dto.StudentNumberDto
import java.util.*

data class AllAccountDto(
    val accountIdx: UUID,
    val name: String,
    val studentNum: StudentNumberDto,
    val profileUrl: String?,
    val authority: Authority,
    val isBlackList: Boolean
)