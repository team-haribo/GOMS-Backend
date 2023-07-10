package com.project.goms.domain.outing.common.util.impl

import com.project.goms.domain.account.entity.Account
import com.project.goms.domain.account.presentation.data.response.StudentNumResponse
import com.project.goms.domain.outing.common.util.OutingConverter
import com.project.goms.domain.outing.entity.Outing
import com.project.goms.domain.outing.presentation.data.response.OutingAccountResponse
import com.project.goms.domain.outing.presentation.data.response.OutingCountResponse
import com.project.goms.domain.outing.usecase.dto.OutingAccountDto
import org.springframework.stereotype.Component
import java.time.format.DateTimeFormatter

@Component
class OutingConverterImpl: OutingConverter {

    override fun toEntity(account: Account): Outing =
        Outing(idx = -1, account = account)

    override fun toResponse(dto: List<OutingAccountDto>): List<OutingAccountResponse> =
        dto.map {
            OutingAccountResponse(
                accountIdx = it.accountIdx,
                name = it.name,
                studentNum = StudentNumResponse(it.studentNum.grade, it.studentNum.classNum, it.studentNum.number),
                profileUrl = it.profileUrl,
                createdTime = it.createdTime.hour.toString() + ":" + it.createdTime.minute
            )
        }

    override fun toResponse(outingCount: Long): OutingCountResponse =
        OutingCountResponse(outingCount = outingCount)


}