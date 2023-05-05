package com.project.goms.domain.auth

import com.project.goms.common.AnyValueObjectGenerator
import com.project.goms.domain.account.entity.Account
import com.project.goms.domain.account.entity.repository.AccountRepository
import com.project.goms.domain.auth.entity.RefreshToken
import com.project.goms.domain.auth.entity.repository.RefreshTokenRepository
import com.project.goms.domain.auth.usecase.dto.TokenDto
import com.project.goms.domain.auth.usecase.ReissueTokenUseCase
import com.project.goms.global.security.jwt.JwtGenerator
import com.project.goms.global.security.jwt.JwtParser
import com.project.goms.global.security.jwt.common.exception.ExpiredRefreshTokenException
import com.project.goms.global.security.jwt.common.exception.InvalidTokenTypeException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.data.repository.findByIdOrNull

class ReissueTokenUseCaseTest: BehaviorSpec({
    val accountRepository = mockk<AccountRepository>()
    val refreshTokenRepository = mockk<RefreshTokenRepository>()
    val jwtGenerator = mockk<JwtGenerator>()
    val jwtParser = mockk<JwtParser>()
    val reissueTokenUseCase = ReissueTokenUseCase(accountRepository, refreshTokenRepository, jwtGenerator, jwtParser)

    Given("refreshToken이 주어졌을때") {
        val refreshToken = "Bearer test refreshToken"
        val parsedRefreshToken = "test refreshToken"
        val email = "test@test.com"
        val tokenDto = AnyValueObjectGenerator.anyValueObject<TokenDto>("refreshToken" to parsedRefreshToken)
        val refreshTokenEntity = AnyValueObjectGenerator.anyValueObject<RefreshToken>("refreshToken" to parsedRefreshToken)
        val account = AnyValueObjectGenerator.anyValueObject<Account>("email" to email)

        every { jwtParser.parseRefreshToken(refreshToken) } returns parsedRefreshToken
        every { refreshTokenRepository.findByIdOrNull(parsedRefreshToken) } returns refreshTokenEntity
        every { accountRepository.findByIdOrNull(refreshTokenEntity.accountIdx) } returns account
        every { jwtGenerator.generateToken(account.idx, account.authority) } returns tokenDto

        When("토큰 재발급을 요청하면") {
            val result = reissueTokenUseCase.execute(refreshToken)

            Then("토큰이 재발급 되어야 한다.") {
                verify(exactly = 1) { jwtGenerator.generateToken(account.idx, account.authority) }
            }

            Then("result와 tokenDto는 같아야 한다.") {
                result shouldBe tokenDto
            }
        }

        When("유효하지 않은 토큰으로 요청하면") {
            every { jwtParser.parseRefreshToken(refreshToken) } returns null

            Then("InvalidTokenTypeException이 터져야 한다.") {
                shouldThrow<InvalidTokenTypeException> {
                    reissueTokenUseCase.execute(refreshToken)
                }
            }
        }

        When("만료된 삭제된 토큰으로 요청하면") {
            every { jwtParser.parseRefreshToken(refreshToken) } returns parsedRefreshToken
            every { refreshTokenRepository.findByIdOrNull(parsedRefreshToken) } returns null

            Then("ExpiredRefreshTokenException이 터져야 한다.") {
                shouldThrow<ExpiredRefreshTokenException> {
                    reissueTokenUseCase.execute(refreshToken)
                }
            }
        }
    }

})