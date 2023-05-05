package com.project.goms.domain.auth.usecase

import com.project.goms.domain.account.entity.Account
import com.project.goms.domain.account.entity.repository.AccountRepository
import com.project.goms.domain.account.entity.Authority
import com.project.goms.domain.auth.common.exception.GAuthException
import com.project.goms.domain.auth.usecase.dto.SignInDto
import com.project.goms.domain.auth.usecase.dto.TokenDto
import com.project.goms.global.annotation.UseCaseWithTransaction
import com.project.goms.global.gauth.property.GAuthProperties
import com.project.goms.global.security.jwt.JwtGenerator
import gauth.GAuth
import gauth.GAuthUserInfo
import mu.KotlinLogging
import java.util.*

private val log = KotlinLogging.logger {  }

@UseCaseWithTransaction
class SignInUseCase(
    private val gAuth: GAuth,
    private val accountRepository: AccountRepository,
    private val jwtGenerator: JwtGenerator,
    private val gAuthProperties: GAuthProperties
) {

    fun execute(dto: SignInDto): TokenDto {
        runCatching {
            gAuth.generateToken(
                dto.code,
                gAuthProperties.clientId,
                gAuthProperties.clientSecret,
                gAuthProperties.redirectUri
            )
        }.onFailure {
            throw GAuthException()
        }.onSuccess {
            log.info { "GAuth Token is ${it.accessToken}" }
            val gAuthInfo =  gAuth.getUserInfo(it.accessToken)
            log.info { "GAuth email is ${gAuthInfo.email}" }
            val account = accountRepository.findByEmail(gAuthInfo.email)  ?: saveAccount(gAuthInfo)
            return jwtGenerator.generateToken(account.idx, account.authority)
        }

        throw GAuthException()
    }

    private fun saveAccount(gAuthInfo: GAuthUserInfo): Account {
        val account = Account(
            idx = UUID.randomUUID(),
            email = gAuthInfo.email,
            name = gAuthInfo.name,
            grade = gAuthInfo.grade,
            classNum = gAuthInfo.classNum,
            number = gAuthInfo.num,
            profileUrl = gAuthInfo.profileUrl,
            authority = Authority.ROLE_STUDENT
        )
        return accountRepository.save(account)
    }

}
