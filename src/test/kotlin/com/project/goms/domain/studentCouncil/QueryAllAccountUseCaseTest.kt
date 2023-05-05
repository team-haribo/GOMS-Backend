package com.project.goms.domain.studentCouncil

import com.project.goms.common.AnyValueObjectGenerator
import com.project.goms.domain.account.entity.Account
import com.project.goms.domain.account.entity.repository.AccountRepository
import com.project.goms.domain.account.usecase.dto.AccountDto
import com.project.goms.domain.studentCouncil.usecase.QueryAllAccountUseCase
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import java.util.UUID

class QueryAllAccountUseCaseTest: BehaviorSpec({
    val accountRepository = mockk<AccountRepository>()
    val queryAllAccountUseCase = QueryAllAccountUseCase(accountRepository)

    Given("계정이 주어질때") {
        val accountIdx = UUID.randomUUID()
        val account = AnyValueObjectGenerator.anyValueObject<Account>("idx" to accountIdx)
        val accountDto = AnyValueObjectGenerator.anyValueObject<AccountDto>("accountIdx" to accountIdx)

        every { accountRepository.findAllByOrderByGradeAscClassNumAscNumberAsc() } returns listOf(account)

        When("모든 계정 조회 요청을 하면") {
            val result = queryAllAccountUseCase.execute()

            Then("result와 accountDto는 같아야 한다.") {
                result shouldBe listOf(accountDto)
            }
        }
    }
})