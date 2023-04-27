package com.project.goms.domain.late.usecase

import com.project.goms.domain.late.common.exception.LateAccountNotFoundException
import com.project.goms.domain.late.persistence.repository.LateRepository
import com.project.goms.domain.late.presentation.data.dto.LateRankDto
import com.project.goms.global.annotation.UseCaseWithReadOnlyTransaction

@UseCaseWithReadOnlyTransaction
class QueryLateRankUseCase(
    private val lateRepository: LateRepository
) {

    fun execute(): List<LateRankDto> {
        val lateRank = lateRepository.findTop5ByOrderByAccountDesc()

        if (lateRank.isEmpty()) {
            throw LateAccountNotFoundException()
        }

        return lateRank.map {
            LateRankDto(
                accountIdx = it.account.idx,
                name = it.account.name,
                studentNum = LateRankDto.StudentNum(it.account.grade, it.account.classNum, it.account.number),
                profileUrl = it.account.profileUrl
            )
        }
    }

}