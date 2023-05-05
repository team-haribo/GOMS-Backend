package com.project.goms.domain.auth

import com.project.goms.common.AnyValueObjectGenerator
import com.project.goms.domain.account.entity.Account
import com.project.goms.domain.account.entity.repository.AccountRepository
import com.project.goms.domain.auth.usecase.dto.SignInDto
import com.project.goms.domain.auth.usecase.dto.TokenDto
import com.project.goms.domain.auth.usecase.SignInUseCase
import com.project.goms.global.gauth.property.GAuthProperties
import com.project.goms.global.security.jwt.JwtGenerator
import gauth.GAuth
import gauth.GAuthToken
import gauth.GAuthUserInfo
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class SignInUseCaseTest : BehaviorSpec({
    val clientId = "test clientId"
    val clientSecret = "test clientSecret"
    val redirectUri = "test redirectUrl"
    val code = "test GAuthCode"

    val gAuth = mockk<GAuth>()
    val accountRepository = mockk<AccountRepository>()
    val jwtGenerator = mockk<JwtGenerator>()
    val gAuthProperties = GAuthProperties(clientId, clientSecret, redirectUri)
    val signInUseCase = SignInUseCase(gAuth, accountRepository, jwtGenerator, gAuthProperties)

    Given("signInDto가 주어질때") {
        val signInDto = SignInDto(code = code)
        val account = AnyValueObjectGenerator.anyValueObject<Account>("email" to "test@test.com")
        val gAuthToken = GAuthToken(mapOf("accessToken" to "test accessToken", "refreshToken" to "test refreshToken"))
        val gAuthInfo = GAuthUserInfo(
            mapOf(
                "email" to account.email,
                "name" to "김시훈",
                "grade" to 3,
                "classNum" to 4,
                "num" to 2,
                "gender" to "MALE",
                "profileUrl" to null,
                "role" to "ROLE_STUDENT"
            )
        )
        val tokenDto = AnyValueObjectGenerator.anyValueObject<TokenDto>("accessToken" to "test accessToken")

        every {
            gAuth.generateToken(
                signInDto.code, gAuthProperties.clientId, gAuthProperties.clientSecret, gAuthProperties.redirectUri
            )
        } returns gAuthToken
        every { gAuth.getUserInfo(gAuthToken.accessToken) } returns gAuthInfo
        every { accountRepository.findByEmail(gAuthInfo.email) } returns null
        every { accountRepository.save(any()) } returns account
        every { jwtGenerator.generateToken(account.idx, account.authority) } returns tokenDto

        When("첫 로그인 시도를 요청하면") {
            val result = signInUseCase.execute(signInDto)

            Then("계정이 저장 되어야 한다.") {
                verify(exactly = 1) { jwtGenerator.generateToken(account.idx, account.authority) }
            }

            Then("result와 tokenDto는 같아야 한다.") {
                result shouldBe tokenDto
            }
        }

        When("로그인 시도 했었던 계정이 요청하면") {
            every { accountRepository.findByEmail(gAuthInfo.email) } returns account
            val result = signInUseCase.execute(signInDto)

            Then("계정이 저장되지 않아야 한다.") {
                verify { accountRepository.save(account) wasNot called }
            }

            Then("result와 tokenDto는 같아야 한다.") {
                result shouldBe tokenDto
            }
        }
    }
})