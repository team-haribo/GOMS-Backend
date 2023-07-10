package com.project.goms.domain.outing.presentation.data.response

import com.project.goms.domain.account.presentation.data.response.StudentNumResponse
import java.time.LocalDateTime
import java.util.*

data class SearchOutingResponse(
    val accountIdx: UUID,
    val name: String,
    val studentNum: StudentNumResponse,
    val profileUrl: String?,
    val createdTime: String
)
