package com.project.goms.domain.outing.usecase

import com.project.goms.domain.outing.entity.repository.OutingRepository
import com.project.goms.global.annotation.UseCaseWithTransaction

@UseCaseWithTransaction
class DeleteOutingStudentsUseCase(
    private val outingRepository: OutingRepository
) {

    // 지각 하여 복귀 요청을 보내지 않은 학생들을 outing 테이블에서 삭제한다.
    fun execute() = outingRepository.deleteAll()

}