package com.project.goms.domain.account.common.util

import com.project.goms.domain.account.presentation.data.dto.ProfileDto
import com.project.goms.domain.account.presentation.data.response.ProfileResponse

interface AccountConverter {

    fun toResponse(dto: ProfileDto): ProfileResponse

}