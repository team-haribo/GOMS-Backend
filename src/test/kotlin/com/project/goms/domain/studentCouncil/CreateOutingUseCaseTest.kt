package com.project.goms.domain.studentCouncil

import com.project.goms.common.AnyValueObjectGenerator
import com.project.goms.domain.studentCouncil.common.property.OutingUUIDExpTimeProperties
import com.project.goms.domain.studentCouncil.entity.OutingUUID
import com.project.goms.domain.studentCouncil.entity.repository.OutingUUIDRepository
import com.project.goms.domain.studentCouncil.usecase.CreateOutingUseCase
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.UUID

class CreateOutingUseCaseTest: BehaviorSpec({
    val outingUUIDRepository = mockk<OutingUUIDRepository>()
    val outingUUIDExpTimeProperties = mockk<OutingUUIDExpTimeProperties>(relaxed = true)
    val createOutingUseCase = CreateOutingUseCase(outingUUIDRepository, outingUUIDExpTimeProperties)

    Given("outingUUID가 주어질때") {
        val outingUUID = AnyValueObjectGenerator.anyValueObject<OutingUUID>("outingUUID" to UUID.randomUUID())

        every { outingUUIDRepository.save(any()) } returns outingUUID

        When("외출 식별자 저장 요청을 하면") {
            val result = createOutingUseCase.execute()

            Then("외출 UUID는 redis에 저장이 되어야 한다.") {
                verify(exactly = 1) { outingUUIDRepository.save(any()) }
            }

            Then("result와 outingUUID은 같아야 한다.") {
                result shouldBe outingUUID.outingUUID
            }
        }
    }
})