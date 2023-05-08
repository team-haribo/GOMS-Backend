package com.project.goms.domain.outing.scheduler

import com.project.goms.domain.late.usecase.SaveLateAccountUseCase
import com.project.goms.domain.outing.usecase.ReminderOutingUseCase
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class OutingScheduler(
    private val reminderOutingUseCase: ReminderOutingUseCase,
    private val saveRateStudentUseCase: SaveLateAccountUseCase
) {

    @Scheduled(cron = "0 40 18 ? * 3")
    fun outingTenMinuteAgoScheduler() = reminderOutingUseCase.execute()

    @Scheduled(cron = "0 30 7 ? * 3")
    fun checkRateStudent() = saveRateStudentUseCase.execute()

}