package com.project.goms.domain.studentCouncil.usecase

import com.project.goms.domain.account.entity.repository.CustomAccountRepository
import com.project.goms.domain.account.usecase.dto.StudentNumberDto
import com.project.goms.domain.studentCouncil.usecase.dto.AllAccountDto
import com.project.goms.global.annotation.UseCaseWithReadOnlyTransaction
import kotlin.streams.asSequence

@UseCaseWithReadOnlyTransaction
class SearchOutingUseCase(
    private val customAccountRepository: CustomAccountRepository,
) {

    fun execute(name: String): List<AllAccountDto> {
        return customAccountRepository.findAccountByStudentInfo(null, null, name, null).stream().asSequence()
           .map {
                AllAccountDto(
                    accountIdx = it.idx,
                    name = it.name,
                    studentNum = StudentNumberDto(it.studentNum.grade, it.studentNum.classNum, it.studentNum.number),
                    profileUrl = it.profileUrl,
                    authority = it.authority,
                    isBlackList = false
                )
            }.toList()
    }

}