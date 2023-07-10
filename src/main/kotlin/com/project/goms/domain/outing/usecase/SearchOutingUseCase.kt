package com.project.goms.domain.outing.usecase

import com.project.goms.domain.account.entity.repository.CustomAccountRepository
import com.project.goms.domain.account.usecase.dto.StudentNumberDto
import com.project.goms.domain.outing.entity.repository.OutingRepository
import com.project.goms.domain.outing.usecase.dto.SearchOutingDto
import com.project.goms.global.annotation.UseCaseWithReadOnlyTransaction
import kotlin.streams.asSequence

@UseCaseWithReadOnlyTransaction
class SearchOutingUseCase(
    private val outingRepository: OutingRepository
) {

    fun execute(name: String): List<SearchOutingDto> {
         return outingRepository.findByAccount_NameContaining(name)
             .map {
               SearchOutingDto(
                    accountIdx = it.account.idx,
                    name = it.account.name,
                    studentNum = StudentNumberDto(it.account.studentNum.grade, it.account.studentNum.classNum, it.account.studentNum.number),
                    profileUrl = it.account.profileUrl,
                    createdTime = it.createdTime
               )
            }.toList()
    }

}