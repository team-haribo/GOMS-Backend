package com.project.goms.domain.account.usecase

import com.project.goms.domain.account.common.util.AccountUtil
import com.project.goms.domain.account.usecase.dto.ProfileDto
import com.project.goms.domain.account.usecase.dto.StudentNumberDto
import com.project.goms.domain.late.entity.repository.LateRepository
import com.project.goms.domain.outing.entity.repository.OutingBlackListRepository
import com.project.goms.global.annotation.UseCaseWithTransaction

@UseCaseWithTransaction
class QueryAccountProfileUseCase(
    private val accountUtil: AccountUtil,
    private val lateRepository: LateRepository,
    private val outingBlackListRepository: OutingBlackListRepository
) {

    fun execute(): ProfileDto {
        val account = accountUtil.getCurrentAccount()
        val lateCount = lateRepository.countByAccountIdx(account.idx)
        return ProfileDto(
            accountIdx = account.idx,
            name = account.name,
            studentNum = StudentNumberDto(
                account.studentNum.grade,
                classNum = account.studentNum.classNum,
                number = account.studentNum.number
            ),
            profileUrl = account.profileUrl,
            lateCount = lateCount,
            isBlackList = outingBlackListRepository.existsById(account.idx)
        )
    }

}