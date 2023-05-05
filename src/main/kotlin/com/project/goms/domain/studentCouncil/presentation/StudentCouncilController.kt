package com.project.goms.domain.studentCouncil.presentation

import com.project.goms.domain.account.entity.Authority
import com.project.goms.domain.account.presentation.data.response.AccountResponse
import com.project.goms.domain.studentCouncil.common.util.StudentCouncilConverter
import com.project.goms.domain.studentCouncil.presentation.data.request.GrantAuthorityRequest
import com.project.goms.domain.studentCouncil.usecase.GrantAuthorityUseCase
import com.project.goms.domain.studentCouncil.usecase.QueryAllAccountUseCase
import com.project.goms.domain.studentCouncil.usecase.SaveBlackListAccountUseCase
import com.project.goms.domain.studentCouncil.usecase.SearchAccountUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/student-council")
class StudentCouncilController(
    private val studentCouncilConverter: StudentCouncilConverter,
    private val grantAuthorityUseCase: GrantAuthorityUseCase,
    private val queryAllAccountUseCase: QueryAllAccountUseCase,
    private val saveBlackListAccountUseCase: SaveBlackListAccountUseCase,
    private val searchAccountUseCase: SearchAccountUseCase,
) {

    @GetMapping("account")
    fun queryAllAccount(): ResponseEntity<List<AccountResponse>> =
        queryAllAccountUseCase.execute()
            .let { studentCouncilConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @PatchMapping("authority")
    fun grantAuthority(@RequestBody request: GrantAuthorityRequest): ResponseEntity<Void> =
        studentCouncilConverter.toDto(request)
            .let { grantAuthorityUseCase.execute(it) }
            .let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

    @PostMapping("black-list/{accountIdx}")
    fun grantAuthority(@PathVariable accountIdx: UUID): ResponseEntity<Void> =
        saveBlackListAccountUseCase.execute(accountIdx)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @GetMapping("search")
    fun searchAccount(
        @RequestParam grade: Int?,
        @RequestParam classNum: Int?,
        @RequestParam name: String?,
        @RequestParam isBlackList: Boolean?,
        @RequestParam authority: Authority?
    ): ResponseEntity<List<AccountResponse>> =
        searchAccountUseCase.execute(grade, classNum, name, isBlackList, authority)
            .let { studentCouncilConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

}