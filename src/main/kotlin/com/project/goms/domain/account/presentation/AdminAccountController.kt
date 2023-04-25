package com.project.goms.domain.account.presentation

import com.project.goms.domain.account.common.util.AccountConverter
import com.project.goms.domain.account.presentation.data.enums.Authority
import com.project.goms.domain.account.presentation.data.request.GrantAuthorityRequest
import com.project.goms.domain.account.presentation.data.response.AccountResponse
import com.project.goms.domain.account.usecase.GrantAuthorityUseCase
import com.project.goms.domain.account.usecase.QueryAllAccountUseCase
import com.project.goms.domain.account.usecase.SearchAccountUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/admin")
class AdminAccountController(
    private val accountConverter: AccountConverter,
    private val grantAuthorityUseCase: GrantAuthorityUseCase,
    private val queryAllAccountUseCase: QueryAllAccountUseCase,
    private val searchAccountUseCase: SearchAccountUseCase
) {

    @GetMapping("account")
    fun queryAllAccount(): ResponseEntity<List<AccountResponse>> =
        queryAllAccountUseCase.execute()
            .let { accountConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @PatchMapping("authority")
    fun grantAuthority(@RequestBody request: GrantAuthorityRequest): ResponseEntity<Void> =
        accountConverter.toDto(request)
            .let { grantAuthorityUseCase.execute(it) }
            .let { ResponseEntity.ok().build() }

    @GetMapping("search")
    fun searchAccount(
        @RequestParam grade: Int?,
        @RequestParam classNum: Int?,
        @RequestParam name: String?,
        @RequestParam isBlackList: Boolean?,
        @RequestParam authority: Authority?
    ): ResponseEntity<List<AccountResponse>> =
        searchAccountUseCase.execute(grade, classNum, name, isBlackList, authority)
            .let { accountConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

}