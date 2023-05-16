package com.project.goms.domain.studentCouncil.usecase

import com.project.goms.domain.account.entity.repository.AccountRepository
import com.project.goms.domain.account.usecase.dto.StudentNumberDto
import com.project.goms.domain.outing.entity.repository.OutingBlackListRepository
import com.project.goms.domain.studentCouncil.usecase.dto.AllAccountDto
import com.project.goms.global.annotation.UseCaseWithReadOnlyTransaction

@UseCaseWithReadOnlyTransaction
class QueryAllAccountUseCase(
    private val accountRepository: AccountRepository,
    private val outingBlackListRepository: OutingBlackListRepository
) {

    fun execute(): List<AllAccountDto> =
        accountRepository.findAllByOrderByGradeAscClassNumAscNumberAsc()
            .map {
                AllAccountDto(
                    accountIdx = it.idx,
                    name = it.name,
                    studentNum = StudentNumberDto(it.grade, it.classNum, it.number),
                    profileUrl = it.profileUrl,
                    authority = it.authority,
                    isBlackList = outingBlackListRepository.existsById(it.idx)
                )
            }

}