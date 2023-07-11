package com.project.goms.domain.outing.presentation

import com.project.goms.domain.outing.common.util.OutingConverter
import com.project.goms.domain.outing.presentation.data.response.OutingAccountResponse
import com.project.goms.domain.outing.presentation.data.response.OutingAvailableResponse
import com.project.goms.domain.outing.presentation.data.response.OutingCountResponse
import com.project.goms.domain.outing.usecase.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/v1/outing")
class OutingController(
    private val outingConverter: OutingConverter,
    private val outingUseCase: OutingUseCase,
    private val queryOutingAccountUseCase: QueryOutingAccountUseCase,
    private val queryOutingCountUseCase: QueryOutingCountUseCase,
    private val searchOutingUseCase: SearchOutingUseCase,
    private val validateOutingTimeUseCase: ValidateOutingTimeUseCase
) {

    @PostMapping("{outingUUID}")
    fun outing(@PathVariable outingUUID: UUID): ResponseEntity<Void> =
        outingUseCase.execute(outingUUID)
            .let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

    @GetMapping
    fun queryOutingAccount(): ResponseEntity<List<OutingAccountResponse>> =
        queryOutingAccountUseCase.execute()
            .let { outingConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @GetMapping("count")
    fun queryOutingCount(): ResponseEntity<OutingCountResponse> =
        queryOutingCountUseCase.execute()
            .let { outingConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @GetMapping("search")
    fun searchOuting(@RequestParam name: String?): ResponseEntity<List<OutingAccountResponse>> =
        searchOutingUseCase.execute(name)
            .let { outingConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @GetMapping("validation")
    fun validateOuting(): ResponseEntity<OutingAvailableResponse> =
        validateOutingTimeUseCase.execute()
            .let { outingConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

}