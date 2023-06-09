package com.project.goms.domain.outing.presentation.data.response

import com.project.goms.domain.account.presentation.data.response.StudentNumResponse
import java.util.*

data class OutingAccountResponse(
    val accountIdx: UUID,
    val name: String,
    val studentNum: StudentNumResponse,
    val profileUrl: String?,
    val createdTime: String
)