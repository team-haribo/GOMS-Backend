package com.project.goms.domain.account.presentation

import com.project.goms.domain.account.presentation.data.request.SignInRequest
import com.project.goms.domain.account.service.SignInService
import com.project.goms.domain.account.common.util.AccountConverter
import com.project.goms.domain.account.presentation.data.response.TokenResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AccountController(
    private val accountConverter: AccountConverter,
    private val signInService: SignInService
) {

    @PostMapping("signin")
    fun signIn(@RequestBody request: SignInRequest): ResponseEntity<TokenResponse> =
        accountConverter.toDto(request)
            .let { signInService.execute(it) }
            .let { accountConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

}