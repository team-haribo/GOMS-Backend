package com.project.goms.domain.outing.usecase

import com.project.goms.domain.account.usecase.dto.StudentNumberDto
import com.project.goms.domain.outing.entity.repository.OutingRepository
import com.project.goms.domain.outing.usecase.dto.OutingAccountDto
import com.project.goms.global.annotation.UseCaseWithReadOnlyTransaction

@UseCaseWithReadOnlyTransaction
class QueryOutingAccountUseCase(
    private val outingRepository: OutingRepository
) {

    fun execute(): List<OutingAccountDto> {
        val outing = outingRepository.queryAllByOrderByCreatedTimeDesc()

        return outing.map {
            val createdTime = it.createdTime
            OutingAccountDto(
                accountIdx = it.account.idx,
                name = it.account.name,
                studentNum = StudentNumberDto(
                    it.account.studentNum.grade,
                    it.account.studentNum.classNum,
                    it.account.studentNum.number
                ),
                profileUrl = it.account.profileUrl,
                createdTime = createdTime.hour.toString() + ":" + createdTime.minute
            )
        }
    }

}