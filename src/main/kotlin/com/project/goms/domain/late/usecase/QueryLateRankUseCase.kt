package com.project.goms.domain.late.usecase

import com.project.goms.domain.late.entity.repository.CustomLateRepository
import com.project.goms.domain.late.usecase.dto.LateRankDto
import com.project.goms.global.annotation.UseCaseWithReadOnlyTransaction

@UseCaseWithReadOnlyTransaction
class QueryLateRankUseCase(
    private val customLateRepository: CustomLateRepository
) {

    fun execute(): List<LateRankDto> = customLateRepository.findTop3ByOrderByAccountDesc()

}