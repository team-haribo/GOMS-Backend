package com.project.goms.domain.outing.usecase

import com.project.goms.global.annotation.UseCaseWithTransaction

@UseCaseWithTransaction
class ReminderOutingUseCase(
    private val sendMessageUseCase: SendMessageUseCase
) {

    fun execute() = sendMessageUseCase.sendMessage()

}