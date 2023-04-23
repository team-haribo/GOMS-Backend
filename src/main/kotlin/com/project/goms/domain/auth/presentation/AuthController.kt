package com.project.goms.domain.auth.presentation

import com.project.goms.domain.auth.common.util.AuthConverter
import com.project.goms.domain.auth.presentation.data.request.SignInRequest
import com.project.goms.domain.auth.presentation.data.response.TokenResponse
import com.project.goms.domain.auth.usecase.ReissueTokenUseCase
import com.project.goms.domain.auth.usecase.SignInUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/auth")
class AuthController(
    private val authConverter: AuthConverter,
    private val signInUseCase: SignInUseCase,
    private val reissueTokenUseCase: ReissueTokenUseCase
) {

    @PostMapping("signin")
    fun signIn(@RequestBody request: SignInRequest): ResponseEntity<TokenResponse> =
        authConverter.toDto(request)
            .let { signInUseCase.execute(it) }
            .let { authConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @PatchMapping
    fun reissueToken(@RequestHeader refreshToken: String): ResponseEntity<TokenResponse> =
        reissueTokenUseCase.execute(refreshToken)
            .let { authConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

}