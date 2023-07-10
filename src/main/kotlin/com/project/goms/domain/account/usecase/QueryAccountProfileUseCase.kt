package com.project.goms.domain.account.usecase

import com.project.goms.domain.account.common.util.AccountUtil
import com.project.goms.domain.account.usecase.dto.ProfileDto
import com.project.goms.domain.account.usecase.dto.StudentNumberDto
import com.project.goms.domain.late.entity.repository.LateRepository
import com.project.goms.domain.outing.entity.repository.OutingBlackListRepository
import com.project.goms.domain.outing.entity.repository.OutingRepository
import com.project.goms.global.annotation.UseCaseWithTransaction

@UseCaseWithTransaction
class QueryAccountProfileUseCase(
    private val accountUtil: AccountUtil,
    private val lateRepository: LateRepository,
    private val outingRepository: OutingRepository,
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
            authority = account.authority,
            profileUrl = account.profileUrl,
            lateCount = lateCount,
            isOuting = outingRepository.existsByAccount(account),
            isBlackList = outingBlackListRepository.existsById(account.idx)
        )
    }

}