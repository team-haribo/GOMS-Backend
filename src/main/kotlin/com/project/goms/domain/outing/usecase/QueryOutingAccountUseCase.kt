package com.project.goms.domain.outing.usecase

import com.project.goms.domain.outing.common.exception.OutingAccountNotFoundException
import com.project.goms.domain.outing.persistence.repository.OutingRepository
import com.project.goms.domain.outing.presentation.data.dto.OutingAccountDto
import com.project.goms.global.annotation.UseCaseWithReadOnlyTransaction

@UseCaseWithReadOnlyTransaction
class QueryOutingAccountUseCase(
    private val outingRepository: OutingRepository
) {

    fun execute(): List<OutingAccountDto> {
        val outing = outingRepository.queryAllByOrderByCreatedTimeDesc()

        if (outing.isEmpty()) {
            throw OutingAccountNotFoundException()
        }

        return outing.map {
            OutingAccountDto(
                it.account.idx,
                OutingAccountDto.StudentNum(it.account.grade, it.account.classNum, it.account.number),
                it.account.profileUrl
            )
        }
    }

}