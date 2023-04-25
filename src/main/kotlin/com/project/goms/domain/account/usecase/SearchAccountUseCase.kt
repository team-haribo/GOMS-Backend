package com.project.goms.domain.account.usecase

import com.project.goms.domain.account.persistence.repository.AccountRepository
import com.project.goms.domain.account.presentation.data.dto.AccountDto
import com.project.goms.domain.account.presentation.data.enums.Authority
import com.project.goms.domain.outing.persistence.repository.OutingBlackListRepository
import com.project.goms.global.annotation.UseCaseWithReadOnlyTransaction

@UseCaseWithReadOnlyTransaction
class SearchAccountUseCase(
    private val accountRepository: AccountRepository,
    private val outingBlackListRepository: OutingBlackListRepository
) {

    fun execute(grade: Int, classNum: Int, name: String, isBlackList: Boolean, authority: Authority): List<AccountDto> =
        accountRepository.findAllByOrderByGradeAscClassNumAscNumberAsc()
            .asSequence()
            .filter { it.grade == grade }
            .filter { it.classNum == classNum }
            .filter { it.name == name }
            .filter { outingBlackListRepository.existsById(it.idx) }
            .filter { it.authority == authority }
            .map {
                AccountDto(
                    accountIdx = it.idx,
                    studentNum = AccountDto.StudentNum(it.grade, it.classNum, it.number),
                    profileUrl = it.profileUrl,
                    authority = it.authority
                )
            }.toList()

}