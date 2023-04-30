package com.project.goms.domain.outing.usecase

import com.project.goms.domain.outing.entity.repository.OutingRepository
import com.project.goms.global.annotation.UseCaseWithReadOnlyTransaction

@UseCaseWithReadOnlyTransaction
class QueryOutingCountUseCase(
    private val outingRepository: OutingRepository
) {

    fun execute(): Long = outingRepository.count()

}