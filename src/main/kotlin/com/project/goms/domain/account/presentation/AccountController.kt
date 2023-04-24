package com.project.goms.domain.account.presentation

import com.project.goms.domain.account.common.util.AccountConverter
import com.project.goms.domain.account.presentation.data.response.ProfileResponse
import com.project.goms.domain.account.usecase.QueryProfileUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/account")
class AccountController(
    private val accountConverter: AccountConverter,
    private val queryProfileUseCase: QueryProfileUseCase
) {

    @GetMapping("profile")
    fun queryProfile(): ProfileResponse =
        queryProfileUseCase.execute()
            .let { accountConverter.toResponse(it) }

}