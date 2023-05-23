package com.project.goms.domain.studentCouncil.usecase

import com.project.goms.domain.account.entity.repository.CustomAccountRepository
import com.project.goms.domain.account.usecase.dto.StudentNumberDto
import com.project.goms.domain.outing.entity.repository.OutingBlackListRepository
import com.project.goms.domain.studentCouncil.usecase.dto.AllAccountDto
import com.project.goms.global.annotation.UseCaseWithReadOnlyTransaction

@UseCaseWithReadOnlyTransaction
class QueryAllAccountUseCase(
    private val customAccountRepository: CustomAccountRepository,
    private val outingBlackListRepository: OutingBlackListRepository
) {

    fun execute(): List<AllAccountDto> {
        val accountList = customAccountRepository.findAllOrderByStudentNum()
        val outingBlackListIds = outingBlackListRepository.findAll().filterNotNull().map { it.accountIdx }

        return accountList.map {
            AllAccountDto(
                accountIdx = it.idx,
                name = it.name,
                studentNum = StudentNumberDto(it.studentNum.grade, it.studentNum.classNum, it.studentNum.number),
                profileUrl = it.profileUrl,
                authority = it.authority,
                isBlackList = outingBlackListIds.contains(it.idx)
            )
        }
    }

}