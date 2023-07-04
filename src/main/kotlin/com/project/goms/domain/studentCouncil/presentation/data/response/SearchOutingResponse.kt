package com.project.goms.domain.studentCouncil.presentation.data.response

import com.project.goms.domain.account.entity.Authority
import com.project.goms.domain.account.presentation.data.response.StudentNumResponse
import java.util.*

data class SearchOutingResponse(
    val accountIdx: UUID,
    val name: String,
    val studentNum: StudentNumResponse,
    val profileUrl: String?,
    val authority: Authority
)
