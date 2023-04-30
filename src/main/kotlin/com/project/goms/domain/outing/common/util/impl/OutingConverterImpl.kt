package com.project.goms.domain.outing.common.util.impl

import com.project.goms.domain.account.entity.Account
import com.project.goms.domain.outing.entity.Outing
import com.project.goms.domain.outing.common.util.OutingConverter
import com.project.goms.domain.outing.presentation.data.dto.OutingAccountDto
import com.project.goms.domain.outing.presentation.data.response.OutingAccountResponse
import com.project.goms.domain.outing.presentation.data.response.OutingCountResponse
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class OutingConverterImpl : OutingConverter {

    override fun toEntity(account: Account): Outing =
        Outing(idx = -1, account = account, createdTime = LocalDateTime.now())

    override fun toResponse(dto: List<OutingAccountDto>): List<OutingAccountResponse> =
        dto.map {
            OutingAccountResponse(
                accountIdx = it.accountIdx,
                name = it.name,
                studentNum = OutingAccountResponse.StudentNum(it.studentNum.grade, it.studentNum.classNum, it.studentNum.number),
                profileUrl = it.profileUrl
            )
        }

    override fun toResponse(outingCount: Long): OutingCountResponse =
        OutingCountResponse(outingCount = outingCount)

}