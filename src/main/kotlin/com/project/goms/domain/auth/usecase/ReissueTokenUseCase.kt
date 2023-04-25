package com.project.goms.domain.auth.usecase

import com.project.goms.domain.account.common.exception.AccountNotFoundException
import com.project.goms.domain.account.persistence.repository.AccountRepository
import com.project.goms.domain.auth.persistence.repository.RefreshTokenRepository
import com.project.goms.domain.auth.presentation.data.dto.TokenDto
import com.project.goms.global.annotation.UseCaseWithTransaction
import com.project.goms.global.security.jwt.JwtGenerator
import com.project.goms.global.security.jwt.JwtParser
import com.project.goms.global.security.jwt.common.exception.ExpiredRefreshTokenException
import com.project.goms.global.security.jwt.common.exception.InvalidTokenTypeException
import org.springframework.data.repository.findByIdOrNull

@UseCaseWithTransaction
class ReissueTokenUseCase(
    private val accountRepository: AccountRepository,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val jwtGenerator: JwtGenerator,
    private val jwtParser: JwtParser
) {

    fun execute(refreshToken: String): TokenDto {
        val parsedRefreshToken = jwtParser.parseRefreshToken(refreshToken) ?: throw InvalidTokenTypeException()
        val refreshTokenEntity = refreshTokenRepository.findByIdOrNull(parsedRefreshToken) ?: throw ExpiredRefreshTokenException()
        val account = accountRepository.findByIdOrNull(refreshTokenEntity.accountIdx) ?: throw AccountNotFoundException()

        return jwtGenerator.generateToken(account.idx, account.authority)
    }

}