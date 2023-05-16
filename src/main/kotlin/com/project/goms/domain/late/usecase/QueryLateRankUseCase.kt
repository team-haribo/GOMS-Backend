package com.project.goms.domain.late.usecase

import com.project.goms.domain.late.entity.repository.LateRepositoryImpl
import com.project.goms.domain.late.usecase.dto.LateRankDto
import com.project.goms.global.annotation.UseCaseWithReadOnlyTransaction

@UseCaseWithReadOnlyTransaction
class QueryLateRankUseCase(
    private val lateRepositoryImpl: LateRepositoryImpl
) {

    fun execute(): List<LateRankDto> = lateRepositoryImpl.findTop5ByOrderByAccountDesc()

}