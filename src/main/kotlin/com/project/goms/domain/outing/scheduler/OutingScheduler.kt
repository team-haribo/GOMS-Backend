package com.project.goms.domain.outing.scheduler

import com.project.goms.domain.late.usecase.SaveLateAccountUseCase
import com.project.goms.domain.outing.usecase.DeleteOutingStudentsUseCase
import com.project.goms.domain.outing.usecase.ReminderOutingUseCase
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class OutingScheduler(
    private val reminderOutingUseCase: ReminderOutingUseCase,
    private val saveRateStudentUseCase: SaveLateAccountUseCase,
    private val deleteOutingStudentsUseCase: DeleteOutingStudentsUseCase,
) {

    @Scheduled(cron = "0 20 18 ? * 3") // 매주 수요일 6시 20분에 외출 여부 디스코드를 보낸다.
    fun sendOutingMessage() = reminderOutingUseCase.execute()

    @Scheduled(cron = "0 25 19 ? * 3") // 매주 수요일 7시 25분에 지각자를 저장한다.
    fun checkRateStudent() = saveRateStudentUseCase.execute()

    @Scheduled(cron = "0 50 19 ? * 3") // 매주 수요일 7시 50분에 외출자를 삭제한다.
    fun deleteOutingStudents() = deleteOutingStudentsUseCase.execute()

}