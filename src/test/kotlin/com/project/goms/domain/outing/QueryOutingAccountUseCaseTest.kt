package com.project.goms.domain.outing

import com.project.goms.common.AnyValueObjectGenerator
import com.project.goms.domain.account.entity.Account
import com.project.goms.domain.outing.entity.Outing
import com.project.goms.domain.outing.entity.repository.OutingRepository
import com.project.goms.domain.outing.usecase.QueryOutingAccountUseCase
import com.project.goms.domain.outing.usecase.dto.OutingAccountDto
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import java.time.LocalTime
import java.util.*

class QueryOutingAccountUseCaseTest: BehaviorSpec({
    val outingRepository = mockk<OutingRepository>()
    val queryOutingAccountUseCase = QueryOutingAccountUseCase(outingRepository)

    Given("외출자가 생길때") {
        val createdTime = LocalTime.now()
        val accountIdx = UUID.randomUUID()
        val account = AnyValueObjectGenerator.anyValueObject<Account>("idx" to accountIdx)
        val outing = AnyValueObjectGenerator.anyValueObject<Outing>("account" to account, "createdTime" to createdTime)
        val outingAccountDto = AnyValueObjectGenerator.anyValueObject<OutingAccountDto>("accountIdx" to accountIdx, "createdTime" to createdTime)

        every { outingRepository.queryAllByOrderByCreatedTimeDesc() } returns listOf(outing)

        When("외출자 목록 요청을 하면") {
            val result = queryOutingAccountUseCase.execute()

            Then("result와 outingAccountDto은 같아야 한다.") {
                result shouldBe listOf(outingAccountDto)
            }
        }
    }
})