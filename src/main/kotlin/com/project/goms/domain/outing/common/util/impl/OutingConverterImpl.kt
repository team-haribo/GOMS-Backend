package com.project.goms.domain.outing.common.util.impl

import com.project.goms.domain.account.persistence.entity.Account
import com.project.goms.domain.outing.persistence.entity.Outing
import com.project.goms.domain.outing.common.util.OutingConverter
import com.project.goms.domain.outing.presentation.data.dto.OutingAccountDto
import com.project.goms.domain.outing.presentation.data.response.OutingAccountResponse
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class OutingConverterImpl : OutingConverter {

    override fun toEntity(account: Account): Outing =
        Outing(idx = -1, account = account, createdTime = LocalDateTime.now())

    override fun toResponse(dto: List<OutingAccountDto>): List<OutingAccountResponse> =
        dto.map {
            OutingAccountResponse(
                it.accountIdx,
                OutingAccountResponse.StudentNum(it.studentNum.grade, it.studentNum.classNum, it.studentNum.number),
                it.profileUrl
            )
        }

}