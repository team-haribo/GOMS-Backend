package com.project.goms.domain.rate.presentation

import com.project.goms.domain.rate.common.util.RateConverter
import com.project.goms.domain.rate.presentation.data.response.RateRankResponse
import com.project.goms.domain.rate.usecase.QueryRateRankUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v2/rate")
class RateController(
    private val rateConverter: RateConverter,
    private val queryRateRankUseCase: QueryRateRankUseCase
) {

    @GetMapping("rank")
    fun queryRateRank(): ResponseEntity<List<RateRankResponse>> =
        queryRateRankUseCase.execute()
            .let { rateConverter.toResponse(it) }
            .let { ResponseEntity.ok(it) }

}