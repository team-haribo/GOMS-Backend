package com.project.goms.domain.outing.usecase.dto

import com.project.goms.domain.account.usecase.dto.StudentNumberDto
import java.time.LocalDateTime
import java.util.*

data class SearchOutingDto (
    val accountIdx: UUID,
    val name: String,
    val studentNum: StudentNumberDto,
    val profileUrl: String?,
    val createdTime: LocalDateTime
)