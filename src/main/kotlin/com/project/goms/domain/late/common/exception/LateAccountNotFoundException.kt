package com.project.goms.domain.late.common.exception

import com.project.goms.global.error.ErrorCode
import com.project.goms.global.error.exception.GomsException

class LateAccountNotFoundException: GomsException(ErrorCode.LATE_ACCOUNT_NOT_FOUND)