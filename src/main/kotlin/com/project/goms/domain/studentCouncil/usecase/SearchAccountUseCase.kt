package com.project.goms.domain.studentCouncil.usecase

import com.project.goms.domain.account.entity.Authority
import com.project.goms.domain.account.entity.repository.AccountRepository
import com.project.goms.domain.account.usecase.dto.StudentNumberDto
import com.project.goms.domain.outing.entity.repository.OutingBlackListRepository
import com.project.goms.domain.studentCouncil.usecase.dto.AllAccountDto
import com.project.goms.global.annotation.UseCaseWithReadOnlyTransaction

@UseCaseWithReadOnlyTransaction
class SearchAccountUseCase(
    private val accountRepository: AccountRepository,
    private val outingBlackListRepository: OutingBlackListRepository
) {

    fun execute(grade: Int?, classNum: Int?, name: String?, isBlackList: Boolean?, authority: Authority?): List<AllAccountDto> =
        accountRepository.findAllByOrderByGradeAscClassNumAscNumberAsc()
            .asSequence()
            .filter {
                if (grade != null) it.grade == grade else true
            }.filter {
                if (classNum != null) it.classNum == classNum else true
            }.filter {
                if (!name.isNullOrBlank()) it.name == name else true
            }.filter {
                if (isBlackList != null) outingBlackListRepository.existsById(it.idx) == isBlackList else true
            }.filter {
                if (authority != null) it.authority == authority else true
            }.map {
                AllAccountDto(
                    accountIdx = it.idx,
                    name = it.name,
                    studentNum = StudentNumberDto(it.grade, it.classNum, it.number),
                    profileUrl = it.profileUrl,
                    authority = it.authority,
                    isBlackList = outingBlackListRepository.existsById(it.idx)
                )
            }.toList()

}