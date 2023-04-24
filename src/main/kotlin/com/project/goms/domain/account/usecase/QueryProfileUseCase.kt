package com.project.goms.domain.account.usecase

import com.project.goms.domain.account.common.util.AccountUtil
import com.project.goms.domain.account.presentation.data.dto.ProfileDto
import com.project.goms.domain.rate.persistence.RateRepository
import com.project.goms.global.annotation.UseCaseWithTransaction

@UseCaseWithTransaction
class QueryProfileUseCase(
    private val accountUtil: AccountUtil,
    private val rateRepository: RateRepository
) {

    fun execute(): ProfileDto {
        val account = accountUtil.getCurrentAccount()
        val rateCount = rateRepository.countByAccountIdx(account.idx)
        return ProfileDto(
            accountIdx = account.idx,
            studentNum = ProfileDto.StudentNum(account.grade, classNum = account.classNum, number = account.number),
            profileUrl = account.profileUrl,
            rateCount = rateCount
        )
    }

}