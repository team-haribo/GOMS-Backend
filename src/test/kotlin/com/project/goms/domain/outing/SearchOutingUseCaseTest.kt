package com.project.goms.domain.outing

import com.project.goms.common.AnyValueObjectGenerator
import com.project.goms.domain.account.entity.Account
import com.project.goms.domain.account.usecase.dto.StudentNumberDto
import com.project.goms.domain.outing.entity.Outing
import com.project.goms.domain.outing.entity.repository.OutingRepository
import com.project.goms.domain.outing.usecase.SearchOutingUseCase
import com.project.goms.domain.outing.usecase.dto.OutingAccountDto
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import java.time.LocalTime
import java.util.*

class SearchOutingUseCaseTest: BehaviorSpec({

    val outingRepository = mockk<OutingRepository>()
    val searchOutingUseCase = SearchOutingUseCase(outingRepository)

    Given("외출자 이름을 검색했을때") {
        val createdTime = LocalTime.now()
        val accountIdx = UUID.randomUUID()
        val account = AnyValueObjectGenerator.anyValueObject<Account>("idx" to accountIdx)
        val outingList = listOf(AnyValueObjectGenerator.anyValueObject<Outing>("account" to account, "createdTime" to createdTime))
        val name = ""
        val outingAccountDto = AnyValueObjectGenerator.anyValueObject<OutingAccountDto>("accountIdx" to accountIdx, "name" to name, "createdTime" to createdTime)

        every { outingRepository.findAll() } returns outingList
        every { outingRepository.queryByAccountNameContaining(name) } returns outingList

        When("이름이 null 이면") {
            val result = searchOutingUseCase.execute(null)

            Then("외출중인 모든 학생이 검색되야 한다.") {
                result shouldBe listOf(outingAccountDto)
            }
        }

        When("외출자 이름을 요청 하면") {
            val result = searchOutingUseCase.execute(name)

            Then("result와 outingDto는 같아야 한다.") {
                result shouldBe listOf(outingAccountDto)
            }
        }
    }
})