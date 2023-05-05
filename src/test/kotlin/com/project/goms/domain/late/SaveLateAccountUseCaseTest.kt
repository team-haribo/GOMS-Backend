package com.project.goms.domain.late

import com.project.goms.common.AnyValueObjectGenerator
import com.project.goms.domain.account.entity.Account
import com.project.goms.domain.late.entity.Late
import com.project.goms.domain.late.entity.repository.LateRepository
import com.project.goms.domain.late.usecase.SaveLateAccountUseCase
import com.project.goms.domain.outing.entity.Outing
import com.project.goms.domain.outing.entity.OutingBlackList
import com.project.goms.domain.outing.entity.repository.OutingBlackListRepository
import com.project.goms.domain.outing.entity.repository.OutingRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.*

class SaveLateAccountUseCaseTest: BehaviorSpec({
    val outingRepository = mockk<OutingRepository>()
    val lateRepository = mockk<LateRepository>()
    val outingBlackListRepository = mockk<OutingBlackListRepository>()
    val saveLateAccountUseCase = SaveLateAccountUseCase(outingRepository, lateRepository, outingBlackListRepository)

    Given("지각생이 생길때") {
        val accountIdx = UUID.randomUUID()
        val account = AnyValueObjectGenerator.anyValueObject<Account>("idx" to accountIdx)
        val outingList = listOf(AnyValueObjectGenerator.anyValueObject<Outing>("account" to account))
        val lateList = listOf(AnyValueObjectGenerator.anyValueObject<Late>("account" to account))
        val outingBlackList = listOf(AnyValueObjectGenerator.anyValueObject<OutingBlackList>("accountIdx" to accountIdx))

        every { outingRepository.findAll() } returns outingList
        every { lateRepository.saveAll(any<List<Late>>()) } returns lateList
        every { outingBlackListRepository.saveAll(any<List<OutingBlackList>>()) } returns outingBlackList

        When("지각생을 저장하는 요청을 하면") {
            saveLateAccountUseCase.execute()

            Then("지각생 테이블에 저장되어야 한다.") {
                verify(exactly = 1) { lateRepository.saveAll(any<List<Late>>()) }
            }

            Then("블랙리스트 테이블에 저장되어야 한다.") {
                verify(exactly = 1) { outingBlackListRepository.saveAll(any<List<OutingBlackList>>()) }
            }
        }
    }
})