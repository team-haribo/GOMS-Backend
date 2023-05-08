package com.project.goms.domain.late.usecase

import com.project.goms.domain.late.entity.Late
import com.project.goms.domain.late.entity.repository.LateRepository
import com.project.goms.domain.outing.entity.OutingBlackList
import com.project.goms.domain.outing.entity.repository.OutingBlackListRepository
import com.project.goms.domain.outing.entity.repository.OutingRepository
import com.project.goms.global.annotation.UseCaseWithTransaction
import mu.KotlinLogging
import java.time.Duration

private val log = KotlinLogging.logger {}

@UseCaseWithTransaction
class SaveLateAccountUseCase(
    private val outingRepository: OutingRepository,
    private val lateRepository: LateRepository,
    private val outingBlackListRepository: OutingBlackListRepository
) {

    fun execute() {
        val outingList = outingRepository.findAll().toList()
        val lateList = ArrayList<Late>(outingList.size)
        val outingBlackList = ArrayList<OutingBlackList>(outingList.size)

        outingList.forEach {
            lateList.add(Late(idx = -1, account = it.account))
            outingBlackList.add(
                OutingBlackList(
                    accountIdx = it.account.idx,
                    expiredAt = Duration.ofDays(7).toSeconds().toInt()
                )
            )
        }

        log.info { "late outing student count is: ${outingList.size}" }

        lateRepository.saveAll(lateList)
        outingBlackListRepository.saveAll(outingBlackList)
    }

}