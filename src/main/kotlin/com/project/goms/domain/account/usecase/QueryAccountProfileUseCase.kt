package com.project.goms.domain.account.usecase

import com.project.goms.domain.account.common.util.AccountUtil
import com.project.goms.domain.account.usecase.dto.ProfileDto
import com.project.goms.domain.late.entity.repository.LateRepository
import com.project.goms.global.annotation.UseCaseWithTransaction

@UseCaseWithTransaction
class QueryAccountProfileUseCase(
    private val accountUtil: AccountUtil,
    private val lateRepository: LateRepository
) {

    fun execute(): ProfileDto {
        val account = accountUtil.getCurrentAccount()
        val lateCount = lateRepository.countByAccountIdx(account.idx)
        return ProfileDto(
            accountIdx = account.idx,
            name = account.name,
            studentNum = ProfileDto.StudentNum(account.grade, classNum = account.classNum, number = account.number),
            profileUrl = account.profileUrl,
            lateCount = lateCount
        )
    }

}