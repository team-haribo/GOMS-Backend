package com.project.goms.domain.account.common.util.impl

import com.project.goms.domain.account.common.util.AccountConverter
import com.project.goms.domain.account.presentation.data.dto.ProfileDto
import com.project.goms.domain.account.presentation.data.response.ProfileResponse
import org.springframework.stereotype.Component

@Component
class AccountConverterImpl : AccountConverter {
    override fun toResponse(dto: ProfileDto): ProfileResponse =
        dto.let {
            ProfileResponse(
                accountIdx = it.accountIdx,
                studentNum = ProfileResponse.StudentNum(
                    it.studentNum.grade,
                    it.studentNum.classNum,
                    it.studentNum.number
                ),
                profileUrl = it.profileUrl,
                rateCount = it.rateCount
            )
        }

}