package com.project.goms.domain.studentCouncil

import com.project.goms.common.AnyValueObjectGenerator
import com.project.goms.domain.account.entity.Account
import com.project.goms.domain.account.entity.Authority
import com.project.goms.domain.account.entity.repository.CustomAccountRepository
import com.project.goms.domain.account.usecase.dto.StudentNumberDto
import com.project.goms.domain.outing.entity.OutingBlackList
import com.project.goms.domain.outing.entity.repository.OutingBlackListRepository
import com.project.goms.domain.studentCouncil.usecase.SearchAccountUseCase
import com.project.goms.domain.studentCouncil.usecase.dto.AllAccountDto
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import java.util.*

class SearchAccountUseCaseTest: BehaviorSpec({
    val customAccountRepository = mockk<CustomAccountRepository>()
    val outingBlackListRepository = mockk<OutingBlackListRepository>()
    val searchAccountUseCase = SearchAccountUseCase(customAccountRepository, outingBlackListRepository)

    Given("계정 검색 키워드가 주어질때") {
        val grade = 0
        val classNum = 0
        val name = ""
        val isBlackList = false
        val authority = Authority.ROLE_STUDENT
        val accountIdx = UUID.randomUUID()
        val account = AnyValueObjectGenerator.anyValueObject<Account>("idx" to accountIdx)
        val allAccountDto = AllAccountDto(
            accountIdx = accountIdx,
            name = "",
            studentNum = StudentNumberDto(0, 0, 0),
            profileUrl = "",
            authority = Authority.ROLE_STUDENT,
            isBlackList = true
        )
        val outingBlackList = AnyValueObjectGenerator.anyValueObject<OutingBlackList>("accountIdx" to account.idx)

        every { customAccountRepository.findAccountByStudentInfo(grade, classNum, name, authority) } returns listOf(account)
        every { outingBlackListRepository.findAll() } returns listOf(outingBlackList)

        When("계정 검색 요청을 하면") {
            val result = searchAccountUseCase.execute(grade, classNum, name, authority, isBlackList)

            Then("result와 accountDto는 같아야 한다.") {
                result shouldBe listOf(allAccountDto)
            }
        }
    }

})