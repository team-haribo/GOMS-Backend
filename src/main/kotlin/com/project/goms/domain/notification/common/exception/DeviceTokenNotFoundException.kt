package com.project.goms.domain.notification.common.exception

import com.project.goms.global.error.ErrorCode
import com.project.goms.global.error.exception.GomsException

class DeviceTokenNotFoundException: GomsException(ErrorCode.DEVICE_TOKEN_NOT_FOUND)