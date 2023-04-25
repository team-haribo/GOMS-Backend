package com.project.goms.domain.outing.presentation

import com.project.goms.domain.outing.common.util.OutingConverter
import com.project.goms.domain.outing.presentation.data.response.OutingAccountResponse
import com.project.goms.domain.outing.usecase.OutingUseCase
import com.project.goms.domain.outing.usecase.QueryOutingAccountUseCase
import com.project.goms.domain.outing.usecase.QueryOutingCountUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/outing")
class OutingController(
    private val outingConverter: OutingConverter,
    private val outingUseCase: OutingUseCase,
    private val queryOutingAccountUseCase: QueryOutingAccountUseCase,
    private val queryOutingCountUseCase: QueryOutingCountUseCase
) {

    @PostMapping
    fun outing(): ResponseEntity<Void> =
        outingUseCase.execute()
            .let { ResponseEntity.ok().build()}

    @GetMapping
    fun queryOutingAccount(): ResponseEntity<List<OutingAccountResponse>> =
        queryOutingAccountUseCase.execute()
            .let { outingConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @GetMapping("count")
    fun queryOutingCount(): ResponseEntity<Long> =
        queryOutingCountUseCase.execute()
            .let { ResponseEntity.ok(it) }

}