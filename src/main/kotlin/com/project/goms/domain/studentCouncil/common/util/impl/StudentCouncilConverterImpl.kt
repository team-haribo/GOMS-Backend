package com.project.goms.domain.studentCouncil.common.util.impl

import com.project.goms.domain.account.presentation.data.response.StudentNumResponse
import com.project.goms.domain.studentCouncil.common.util.StudentCouncilConverter
import com.project.goms.domain.studentCouncil.presentation.data.request.GrantAuthorityRequest
import com.project.goms.domain.studentCouncil.presentation.data.response.AllAccountResponse
import com.project.goms.domain.studentCouncil.presentation.data.response.SearchOutingResponse
import com.project.goms.domain.studentCouncil.usecase.dto.AllAccountDto
import com.project.goms.domain.studentCouncil.usecase.dto.GrantAuthorityDto
import com.project.goms.domain.studentCouncil.usecase.dto.SearchOutingDto
import org.springframework.stereotype.Component

@Component
class StudentCouncilConverterImpl: StudentCouncilConverter {

    override fun toDto(request: GrantAuthorityRequest): GrantAuthorityDto =
        GrantAuthorityDto(accountIdx = request.accountIdx, authority = request.authority)

    override fun toResponseAllAccount(dto: List<AllAccountDto>): List<AllAccountResponse> =
        dto.map {
            AllAccountResponse(
                accountIdx = it.accountIdx,
                name = it.name,
                studentNum = StudentNumResponse(it.studentNum.grade, it.studentNum.classNum, it.studentNum.number),
                profileUrl = it.profileUrl,
                authority = it.authority,
                isBlackList = it.isBlackList
            )
        }

    override fun toResponseSearchOuting(dto: List<SearchOutingDto>): List<SearchOutingResponse> =
        dto.map {
            SearchOutingResponse(
                accountIdx = it.accountIdx,
                name = it.name,
                studentNum = StudentNumResponse(it.studentNum.grade, it.studentNum.classNum, it.studentNum.number),
                profileUrl = it.profileUrl,
                authority = it.authority
            )
        }

}