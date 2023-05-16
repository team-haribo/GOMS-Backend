package com.project.goms.domain.studentCouncil.common.util.impl

import com.project.goms.domain.account.presentation.data.response.AccountResponse
import com.project.goms.domain.account.presentation.data.response.StudentNumResponse
import com.project.goms.domain.account.usecase.dto.AccountDto
import com.project.goms.domain.studentCouncil.common.util.StudentCouncilConverter
import com.project.goms.domain.studentCouncil.usecase.dto.GrantAuthorityDto
import com.project.goms.domain.studentCouncil.presentation.data.request.GrantAuthorityRequest
import com.project.goms.domain.studentCouncil.presentation.data.response.AllAccountResponse
import com.project.goms.domain.studentCouncil.usecase.dto.AllAccountDto
import org.springframework.stereotype.Component

@Component
class StudentCouncilConverterImpl: StudentCouncilConverter {

    override fun toDto(request: GrantAuthorityRequest): GrantAuthorityDto =
        GrantAuthorityDto(accountIdx = request.accountIdx, authority = request.authority)

    override fun toAllAccountListResponse(dto: List<AllAccountDto>): List<AllAccountResponse> =
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

    override fun toAccountListResponse(dto: List<AccountDto>): List<AccountResponse> =
        dto.map {
            AccountResponse(
                accountIdx = it.accountIdx,
                name = it.name,
                studentNum = StudentNumResponse(it.studentNum.grade, it.studentNum.classNum, it.studentNum.number),
                profileUrl = it.profileUrl,
                authority = it.authority
            )
        }

}