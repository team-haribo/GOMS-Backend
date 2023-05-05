package com.project.goms.domain.outing

import com.project.goms.common.AnyValueObjectGenerator
import com.project.goms.domain.account.common.util.AccountUtil
import com.project.goms.domain.account.entity.Account
import com.project.goms.domain.outing.common.exception.BlackListNotAllowOutingException
import com.project.goms.domain.outing.common.util.OutingConverter
import com.project.goms.domain.outing.entity.Outing
import com.project.goms.domain.outing.entity.repository.OutingBlackListRepository
import com.project.goms.domain.outing.entity.repository.OutingRepository
import com.project.goms.domain.outing.usecase.OutingUseCase
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.Called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.UUID

class OutingUseCaseTest: BehaviorSpec({
    val outingRepository = mockk<OutingRepository>()
    val outingBlackListRepository = mockk<OutingBlackListRepository>()
    val outingConverter = mockk<OutingConverter>()
    val accountUtil = mockk<AccountUtil>()
    val outingUseCase = OutingUseCase(outingRepository, outingBlackListRepository, outingConverter, accountUtil)

    Given("계정이 주어질때") {
        val accountIdx = UUID.randomUUID()
        val account = AnyValueObjectGenerator.anyValueObject<Account>("idx" to accountIdx)
        val outing = AnyValueObjectGenerator.anyValueObject<Outing>("account" to account)

        every { accountUtil.getCurrentAccount() } returns account
        every { outingConverter.toEntity(account) } returns outing
        every { outingBlackListRepository.existsById(account.idx) } returns false
        every { outingRepository.existsByAccount(account) } returns false
        every { outingRepository.save(any()) } returns outing
        every { outingRepository.deleteByAccountIdx(account.idx) } returns Unit

        When("첫 외출을 요청할때") {
            outingUseCase.execute()

            Then("계정이 outing 테이블에 저장이 되어야 한다.") {
                verify(exactly = 1) { outingRepository.save(any()) }
            }
        }

        When("외출 요청을 시도 했었던 계정이 요청하면") {
            every { outingRepository.existsByAccount(account) } returns true
            outingUseCase.execute()

            Then("계정이 outing 테이블에 삭제 되어야 한다.") {
                verify(exactly = 1) { outingRepository.deleteByAccountIdx(account.idx) }
            }

            Then("계정이 outing 테이블에 저장이 되면 안된다.") {
                verify { outingRepository.save(any()) wasNot Called}
            }
        }

        When("외출 블랙리스트에 있는 학생이 외출 할 경우") {
            every { outingBlackListRepository.existsById(account.idx) } returns true
            every { outingRepository.existsByAccount(account) } returns false

            Then("BlackListNotAllowOutingException이 터져야 한다.") {
                shouldThrow<BlackListNotAllowOutingException> {
                    outingUseCase.execute()
                }
            }
        }
    }
})