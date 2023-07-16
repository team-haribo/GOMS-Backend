package com.project.goms.domain.studentCouncil

import com.project.goms.common.AnyValueObjectGenerator
import com.project.goms.domain.account.common.exception.AccountNotFoundException
import com.project.goms.domain.account.entity.Account
import com.project.goms.domain.account.entity.repository.AccountRepository
import com.project.goms.domain.outing.entity.Outing
import com.project.goms.domain.outing.entity.repository.OutingRepository
import com.project.goms.domain.studentCouncil.usecase.DeleteOutingUseCase
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.data.repository.findByIdOrNull
import java.util.*

class DeleteOutingUseCaseTest : BehaviorSpec({

    val accountRepository = mockk<AccountRepository>()
    val outingRepository = mockk<OutingRepository>()
    val deleteOutingUseCase = DeleteOutingUseCase(accountRepository, outingRepository)

    Given("outingIdx가 주어졌을때") {
        val outingIdx = UUID.randomUUID()
        val accountIdx = UUID.randomUUID()
        val account = AnyValueObjectGenerator.anyValueObject<Account>("idx" to accountIdx)
        val outing = AnyValueObjectGenerator.anyValueObject<Outing>("account" to account)

        every { accountRepository.findByIdOrNull(outingIdx) } returns account
        every { outingRepository.deleteByAccountIdx(accountIdx) } returns Unit

        When("외출자 리스트 삭제를 요청하면") {
            deleteOutingUseCase.execute(outingIdx)

            Then("외출자 리스트에서 삭제되야 한다.") {
                verify(exactly = 1) { outingRepository.deleteByAccountIdx(outing.account.idx) }
            }
        }

        When("없는 계정 index일 경우") {
            every { accountRepository.findByIdOrNull(accountIdx) } returns null

            Then("AccountNotFound가 터져야 한다.") {
                shouldThrow<AccountNotFoundException> {
                    deleteOutingUseCase.execute(accountIdx)
                }
            }
        }

    }
})