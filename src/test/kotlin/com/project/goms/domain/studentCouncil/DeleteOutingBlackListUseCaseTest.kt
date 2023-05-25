package com.project.goms.domain.studentCouncil

import com.project.goms.common.AnyValueObjectGenerator
import com.project.goms.domain.account.common.exception.AccountNotFoundException
import com.project.goms.domain.account.entity.Account
import com.project.goms.domain.account.entity.repository.AccountRepository
import com.project.goms.domain.outing.entity.OutingBlackList
import com.project.goms.domain.outing.entity.repository.OutingBlackListRepository
import com.project.goms.domain.studentCouncil.usecase.DeleteOutingBlackListUseCase
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.data.repository.findByIdOrNull
import java.util.*

class DeleteOutingBlackListUseCaseTest: BehaviorSpec({
    val accountRepository = mockk<AccountRepository>()
    val outingBlackListRepository = mockk<OutingBlackListRepository>()
    val deleteOutingBlackList = DeleteOutingBlackListUseCase(accountRepository, outingBlackListRepository)

    Given("accountIdx가 주어질때") {
        val accountIdx = UUID.randomUUID()
        val account = AnyValueObjectGenerator.anyValueObject<Account>("idx" to accountIdx)

        every { accountRepository.findByIdOrNull(accountIdx) } returns account
        every { outingBlackListRepository.deleteById(account.idx) } returns Unit

        When("외출 블랙리스트 삭제를 요청하면") {
            deleteOutingBlackList.execute(accountIdx)

            Then("외출 블랙리스트 삭제가 되어야 한다.") {
                verify(exactly = 1) { outingBlackListRepository.deleteById(account.idx) }
            }
        }

        When("없는 계정 index일 경우") {
            every { accountRepository.findByIdOrNull(accountIdx) } returns null

            Then("AccountNotFound가 터져야 한다.") {
                shouldThrow<AccountNotFoundException> {
                    deleteOutingBlackList.execute(accountIdx)
                }
            }
        }
    }
})