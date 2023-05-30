package com.project.goms.domain.account

import com.project.goms.common.AnyValueObjectGenerator
import com.project.goms.domain.account.common.util.AccountUtil
import com.project.goms.domain.account.entity.Account
import com.project.goms.domain.account.usecase.dto.ProfileDto
import com.project.goms.domain.account.usecase.QueryAccountProfileUseCase
import com.project.goms.domain.late.entity.repository.LateRepository
import com.project.goms.domain.outing.entity.repository.OutingBlackListRepository
import com.project.goms.domain.outing.entity.repository.OutingRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class QueryAccountProfileUseCaseTest: BehaviorSpec({
    val accountUtil = mockk<AccountUtil>()
    val lateRepository = mockk<LateRepository>()
    val outingRepository = mockk<OutingRepository>()
    val outingBlackListRepository = mockk<OutingBlackListRepository>()
    val queryAccountProfileUseCase = QueryAccountProfileUseCase(accountUtil, lateRepository, outingRepository, outingBlackListRepository)

    Given("계정이 주어질때") {
        val account = AnyValueObjectGenerator.anyValueObject<Account>("email" to "test@test.com")
        val profileDto = AnyValueObjectGenerator.anyValueObject<ProfileDto>("accountIdx" to account.idx)

        every { accountUtil.getCurrentAccount() } returns account
        every { lateRepository.countByAccountIdx(account.idx) } returns 0
        every { outingRepository.existsByAccount(account) } returns false
        every { outingBlackListRepository.existsById(account.idx) } returns false

        When("프로필 요청을 하면") {
            val result = queryAccountProfileUseCase.execute()

            Then("result와 profileDto는 같아야 한다.") {
                result shouldBe profileDto
            }
        }
    }

})