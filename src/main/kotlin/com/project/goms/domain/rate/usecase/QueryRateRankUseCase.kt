package com.project.goms.domain.rate.usecase

import com.project.goms.domain.rate.persistence.RateRepository
import com.project.goms.domain.rate.presentation.data.dto.RateRankDto
import com.project.goms.global.annotation.UseCaseWithReadOnlyTransaction

@UseCaseWithReadOnlyTransaction
class QueryRateRankUseCase(
    private val rateRepository: RateRepository
) {

    fun execute(): List<RateRankDto> {
        val rateRank = rateRepository.findTop3ByOrderByAccountDesc()
        return rateRank.map {
            RateRankDto(
                accountIdx = it.account.idx,
                RateRankDto.StudentNum(it.account.grade, it.account.classNum, it.account.number),
                profileUrl = it.account.profileUrl
            )
        }
    }

}