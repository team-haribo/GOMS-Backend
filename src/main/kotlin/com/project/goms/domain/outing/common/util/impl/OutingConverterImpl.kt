package com.project.goms.domain.outing.common.util.impl

import com.project.goms.domain.account.entity.Account
import com.project.goms.domain.account.presentation.data.response.StudentNumResponse
import com.project.goms.domain.outing.common.util.OutingConverter
import com.project.goms.domain.outing.entity.Outing
import com.project.goms.domain.outing.presentation.data.response.OutingAccountResponse
import com.project.goms.domain.outing.presentation.data.response.OutingCountResponse
import com.project.goms.domain.outing.usecase.dto.OutingAccountDto
import com.project.goms.domain.studentCouncil.presentation.data.response.SearchOutingResponse
import com.project.goms.domain.studentCouncil.usecase.dto.SearchOutingDto
import org.springframework.stereotype.Component

@Component
class OutingConverterImpl: OutingConverter {

    override fun toEntity(account: Account): Outing =
        Outing(idx = -1, account = account)

    override fun toOutingAccountResponse(dto: List<OutingAccountDto>): List<OutingAccountResponse> =
        dto.map {
            OutingAccountResponse(
                accountIdx = it.accountIdx,
                name = it.name,
                studentNum = StudentNumResponse(it.studentNum.grade, it.studentNum.classNum, it.studentNum.number),
                profileUrl = it.profileUrl,
                createdTime = it.createdTime
            )
        }

    override fun toResponse(outingCount: Long): OutingCountResponse =
        OutingCountResponse(outingCount = outingCount)

    override fun toSearchOutingResponse(dto: List<SearchOutingDto>): List<SearchOutingResponse> =
        dto.map {
            SearchOutingResponse(
                accountIdx = it.accountIdx,
                name = it.name,
                studentNum = StudentNumResponse(it.studentNum.grade, it.studentNum.classNum, it.studentNum.number),
                profileUrl = it.profileUrl
            )
        }

}