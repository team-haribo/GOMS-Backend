package com.project.goms.domain.studentCouncil.presentation

import com.project.goms.domain.account.entity.Authority
import com.project.goms.domain.studentCouncil.common.util.StudentCouncilConverter
import com.project.goms.domain.studentCouncil.presentation.data.request.GrantAuthorityRequest
import com.project.goms.domain.studentCouncil.presentation.data.request.OutingBanRequest
import com.project.goms.domain.studentCouncil.presentation.data.response.AllAccountResponse
import com.project.goms.domain.studentCouncil.usecase.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/student-council")
class StudentCouncilController(
    private val studentCouncilConverter: StudentCouncilConverter,
    private val createOutingUseCase: CreateOutingUseCase,
    private val grantAuthorityUseCase: GrantAuthorityUseCase,
    private val queryAllAccountUseCase: QueryAllAccountUseCase,
    private val saveOutingBlackListUseCase: SaveOutingBlackListUseCase,
    private val deleteOutingBlackListUseCase: DeleteOutingBlackListUseCase,
    private val searchAccountUseCase: SearchAccountUseCase,
    private val outingBanUseCase: OutingBanUseCase
) {

    @PostMapping("outing")
    fun createOuting(): ResponseEntity<Map<String, UUID>> =
        createOutingUseCase.execute()
            .let { ResponseEntity.ok(mapOf("outingUUID" to it)) }

    @GetMapping("account")
    fun queryAllAccount(): ResponseEntity<List<AllAccountResponse>> =
        queryAllAccountUseCase.execute()
            .let { studentCouncilConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @PatchMapping("authority")
    fun grantAuthority(@RequestBody request: GrantAuthorityRequest): ResponseEntity<Void> =
        studentCouncilConverter.toDto(request)
            .let { grantAuthorityUseCase.execute(it) }
            .let { ResponseEntity.status(HttpStatus.RESET_CONTENT).build() }

    @PostMapping("black-list/{accountIdx}")
    fun saveBlackList(@PathVariable accountIdx: UUID): ResponseEntity<Void> =
        saveOutingBlackListUseCase.execute(accountIdx)
            .let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

    @DeleteMapping("black-list/{accountIdx}")
    fun deleteBlackList(@PathVariable accountIdx: UUID): ResponseEntity<Void> =
        deleteOutingBlackListUseCase.execute(accountIdx)
            .let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

    @GetMapping("search")
    fun searchAccount(
        @RequestParam(required = false) grade: Int?,
        @RequestParam(required = false) classNum: Int?,
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) authority: Authority?,
        @RequestParam(required = false) isBlackList: Boolean?
    ): ResponseEntity<List<AllAccountResponse>> =
        searchAccountUseCase.execute(grade, classNum, name, authority, isBlackList)
            .let { studentCouncilConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @PostMapping("/outing/ban")
    fun outingBan(@RequestBody outingBanRequest: OutingBanRequest): ResponseEntity<Void> =
        studentCouncilConverter.toDto(outingBanRequest)
            .let { outingBanUseCase.execute(it) }
            .let { ResponseEntity.status(HttpStatus.RESET_CONTENT).build() }

}