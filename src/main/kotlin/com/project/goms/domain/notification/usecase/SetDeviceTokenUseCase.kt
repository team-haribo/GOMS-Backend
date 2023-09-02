package com.project.goms.domain.notification.usecase

import com.project.goms.domain.account.common.util.AccountUtil
import com.project.goms.domain.notification.entity.DeviceToken
import com.project.goms.domain.notification.entity.repository.DeviceTokenRepository
import com.project.goms.global.annotation.UseCaseWithTransaction
import mu.KotlinLogging

private val log = KotlinLogging.logger {  }

@UseCaseWithTransaction
class SetDeviceTokenUseCase(
    private val accountUtil: AccountUtil,
    private val deviceTokenRepository: DeviceTokenRepository
) {

    fun execute(token: String) {
        val account = accountUtil.getCurrentAccount()

        log.info("deviceToken is $token")

        deviceTokenRepository.save(
            DeviceToken(
                accountIdx = account.idx,
                token = token
            )
        )
    }

}