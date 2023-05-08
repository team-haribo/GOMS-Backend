package com.project.goms.domain.studentCouncil.usecase

import com.project.goms.domain.account.entity.repository.AccountRepository
import com.project.goms.domain.account.usecase.dto.AccountDto
import com.project.goms.domain.account.usecase.dto.StudentNumberDto
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
                    name = it.name,
                    studentNum = StudentNumberDto(it.grade, it.classNum, it.number),
                    profileUrl = it.profileUrl,
                    authority = it.authority
                )
            }

}