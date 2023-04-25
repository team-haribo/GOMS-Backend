package com.project.goms.domain.late.presentation

import com.project.goms.domain.late.common.util.LateConverter
import com.project.goms.domain.late.presentation.data.response.LateRankResponse
import com.project.goms.domain.late.usecase.QueryLateRankUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/late")
class LateController(
    private val lateConverter: LateConverter,
    private val queryLateRankUseCase: QueryLateRankUseCase
) {

    @GetMapping("rank")
    fun queryRateRank(): ResponseEntity<List<LateRankResponse>> =
        queryLateRankUseCase.execute()
            .let { lateConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

}