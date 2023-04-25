package com.project.goms.domain.account.usecase

import com.project.goms.domain.account.persistence.repository.AccountRepository
import com.project.goms.domain.account.presentation.data.dto.AccountDto
import com.project.goms.global.annotation.UseCaseWithReadOnlyTransaction

@UseCaseWithReadOnlyTransaction
class QueryAllAccountUseCase(
    private val accountRepository: AccountRepository
) {

    fun execute() =
        accountRepository.findAllByOrderByGradeAscClassNumAscNumberAsc()
            .map {
                AccountDto(
                    accountIdx = it.idx,
                    studentNum = AccountDto.StudentNum(it.grade, it.classNum, it.number),
                    profileUrl = it.profileUrl,
                    authority = it.authority
                )
            }


}