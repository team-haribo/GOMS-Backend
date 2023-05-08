package com.project.goms.domain.studentCouncil.common.util.impl

import com.project.goms.domain.account.usecase.dto.AccountDto
import com.project.goms.domain.account.presentation.data.response.AccountResponse
import com.project.goms.domain.account.presentation.data.response.StudentNumResponse
import com.project.goms.domain.studentCouncil.common.util.StudentCouncilConverter
import com.project.goms.domain.studentCouncil.usecase.dto.GrantAuthorityDto
import com.project.goms.domain.studentCouncil.presentation.data.request.GrantAuthorityRequest
import org.springframework.stereotype.Component

@Component
class StudentCouncilConverterImpl: StudentCouncilConverter {

    override fun toDto(request: GrantAuthorityRequest): GrantAuthorityDto =
        GrantAuthorityDto(accountIdx = request.accountIdx, authority = request.authority)

    override fun toResponse(dto: List<AccountDto>): List<AccountResponse> =
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