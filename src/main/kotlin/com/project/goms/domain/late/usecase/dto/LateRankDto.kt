package com.project.goms.domain.late.usecase.dto

import com.project.goms.domain.account.usecase.dto.StudentNumberDto
import java.util.*

data class LateRankDto(
    val accountIdx: UUID,
    val name: String,
    val studentNum: StudentNumberDto,
    val profileUrl: String?,
)