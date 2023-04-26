package com.project.goms.domain.outing.scheduler

import com.project.goms.domain.late.usecase.SaveLateAccountUseCase
import com.project.goms.infrastructure.discord.usecase.SendDiscordUseCase
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class OutingScheduler(
    private val sendDiscordUseCase: SendDiscordUseCase,
    private val saveRateStudentUseCase: SaveLateAccountUseCase
) {

    @Scheduled(cron = "0 40 18 ? * 3", zone = "Asia/Seoul")
    fun outingTenMinuteAgoScheduler() = sendDiscordUseCase.execute()

    @Scheduled(cron = "0 30 7 ? * 3", zone = "Asia/Seoul")
    fun checkRateStudent() = saveRateStudentUseCase.execute()

}