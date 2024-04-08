package com.project.goms.infrastructure.notification

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.MulticastMessage
import com.project.goms.domain.notification.entity.Notification
import com.project.goms.domain.notification.spi.NotificationPort
import org.springframework.stereotype.Component

@Component
class FcmAdapter: NotificationPort {

    private val firebaseInstance: FirebaseMessaging
        get() = FirebaseMessaging.getInstance()

    override fun sendNotification(deviceTokens: List<String>, notification: Notification) {
        val message = getMulticastMassageBuilderByNotification(notification)
            .addAllTokens(deviceTokens)
            .build()
        firebaseInstance.sendMulticastAsync(message)
    }

    private fun getMulticastMassageBuilderByNotification(notification: Notification) =
        with(notification) {
            MulticastMessage.builder()
                .setNotification(
                    com.google.firebase.messaging.Notification.builder()
                        .setTitle(title)
                        .setBody(content)
                        .build()
                )
        }

}
