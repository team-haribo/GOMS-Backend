package com.project.goms.domain.notification.usecase

import com.project.goms.domain.notification.common.exception.DeviceTokenNotFoundException
import com.project.goms.domain.notification.spi.NotificationPort
import com.project.goms.domain.late.entity.repository.LateRepository
import com.project.goms.domain.notification.entity.*
import com.project.goms.domain.notification.entity.repository.DeviceTokenRepository
import com.project.goms.domain.outing.entity.repository.OutingRepository
import com.project.goms.global.annotation.UseCaseWithReadOnlyTransaction
import org.springframework.data.repository.findByIdOrNull
import java.time.LocalDate
import java.util.*

@UseCaseWithReadOnlyTransaction
class SendNotificationUseCase(
    private val lateRepository: LateRepository,
    private val notificationPort: NotificationPort,
    private val deviceTokenRepository: DeviceTokenRepository,
    private val outingRepository: OutingRepository,
) {

    fun execute(notificationType: NotificationType) {
        val oneWeekAgo = LocalDate.now().minusWeeks(1)

        when (notificationType) {
            // 외출 전 지난주 지각자 수에 따라서 외출 알림 발송
            NotificationType.BEFORE_OUTING -> {
                runCatching {
                    when (lateRepository.lateCountOntWeekAgo(oneWeekAgo) < 3) {
                        true -> {
                            notificationPort.sendNotification(
                                deviceTokens = deviceTokenRepository.findAll().map { it.token },
                                notification = Notification(
                                    title = Topic.BEFORE_OUTING.title,
                                    content = Topic.BEFORE_OUTING.content,
                                    writer = Writer.GOMS
                                )
                            )
                        }

                        false -> {
                            notificationPort.sendNotification(
                                deviceTokens = deviceTokenRepository.findAll().map { it.token },
                                notification = Notification(
                                    title = Topic.GROUNDED.title,
                                    content = Topic.GROUNDED.content,
                                    writer = Writer.GOMS
                                )
                            )
                        }
                    }
                }.onFailure {
                    it.printStackTrace()
                }

            }

            // 외출 5분 전 아직 복귀하지 않은 학생들에게 알림 발송
            NotificationType.AFTER_OUTING -> {
                runCatching {
                    val outingList = outingRepository.findAll()
                    // 외출자가 없으면 반환하고 끝낸다.
                    if (outingList.toList().isEmpty()) return

                    notificationPort.sendNotification(
                        deviceTokens = outingList.map { findDeviceTokenByAccountIdx(it.account.idx) }.map { it.token },
                        notification = Notification(
                            title = Topic.AFTER_OUTING.title,
                            content = Topic.AFTER_OUTING.content,
                            writer = Writer.GOMS
                        )
                    )
                }.onFailure {
                    it.stackTrace
                }
            }
        }
    }

    private fun findDeviceTokenByAccountIdx(accountIdx: UUID): DeviceToken =
        deviceTokenRepository.findByIdOrNull(accountIdx)
            ?: throw DeviceTokenNotFoundException()

}