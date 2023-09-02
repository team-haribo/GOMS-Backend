package com.project.goms.domain.notification.spi

import com.project.goms.domain.notification.entity.Notification

interface NotificationPort {

    fun sendNotification(deviceTokens: List<String>, notification: Notification)

}