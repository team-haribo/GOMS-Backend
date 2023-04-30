package com.project.goms.domain.late.usecase

import com.project.goms.domain.outing.entity.repository.OutingRepository
import com.project.goms.domain.late.entity.Late
import com.project.goms.domain.late.entity.repository.LateRepository
import com.project.goms.domain.outing.entity.OutingBlackList
import com.project.goms.domain.outing.entity.repository.OutingBlackListRepository
import com.project.goms.global.annotation.UseCaseWithTransaction

@UseCaseWithTransaction
class SaveLateAccountUseCase(
    private val outingRepository: OutingRepository,
    private val lateRepository: LateRepository,
    private val outingBlackListRepository: OutingBlackListRepository
) {

    fun execute() {
        val outingList = outingRepository.findAll()
        val lateList = outingList.map {
            Late(idx = -1, account = it.account)
        }
        val outingBlackList = outingList.map {
            OutingBlackList(accountIdx = it.account.idx, blackListTime = 604800)
        }
        lateRepository.saveAll(lateList)
        outingBlackListRepository.saveAll(outingBlackList)
    }

}