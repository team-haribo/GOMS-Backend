package com.project.goms.domain.outing.common.exception

import com.project.goms.global.error.ErrorCode
import com.project.goms.global.error.exception.GomsException

class OutingUUIDUnverifiedException: GomsException(ErrorCode.OUTING_UUID_UNVERIFIED)