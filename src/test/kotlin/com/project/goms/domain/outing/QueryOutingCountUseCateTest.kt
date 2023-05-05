package com.project.goms.domain.outing

import com.project.goms.domain.outing.entity.repository.OutingRepository
import com.project.goms.domain.outing.usecase.QueryOutingCountUseCase
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class QueryOutingCountUseCateTest: BehaviorSpec({
    val outingRepository = mockk<OutingRepository>()
    val queryOutingCountUseCase = QueryOutingCountUseCase(outingRepository)

    Given("외출자가 생길때") {
        val outingCount = 1L

        every { outingRepository.count() } returns outingCount

        When("외출자 카운트 요청을 하면") {
            val result = queryOutingCountUseCase.execute()

            Then("result와 outingCount는 같아야 한다.") {
                result shouldBe outingCount
            }
        }
    }
})