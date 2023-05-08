package com.project.goms.domain.late

import com.project.goms.common.AnyValueObjectGenerator
import com.project.goms.domain.account.entity.Account
import com.project.goms.domain.late.entity.Late
import com.project.goms.domain.late.entity.repository.LateRepository
import com.project.goms.domain.late.usecase.dto.LateRankDto
import com.project.goms.domain.late.usecase.QueryLateRankUseCase
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import java.util.*

class QueryLateRankUseCaseTest: BehaviorSpec({
    val lateRepository = mockk<LateRepository>()
    val queryLateRankUseCase = QueryLateRankUseCase(lateRepository)

    Given("지각 랭킹 조회하겠다는 마음이 주어질때") {
        val accountIdx = UUID.randomUUID()
        val lateRankDto = AnyValueObjectGenerator.anyValueObject<LateRankDto>("accountIdx" to accountIdx)
        val listLateRankDto = listOf(lateRankDto)
        val account = AnyValueObjectGenerator.anyValueObject<Account>("idx" to accountIdx)
        val late = AnyValueObjectGenerator.anyValueObject<Late>("account" to account)
        val listLate = listOf(late)

        every { lateRepository.findTop5ByOrderByAccountDesc() } returns listLate

        When("지각 랭킹 조회 요청을 하면") {
            val result = queryLateRankUseCase.execute()

            Then("result와 List<lankDto>는 같아야 한다.") {
                result shouldBe listLateRankDto
            }
        }
    }
})