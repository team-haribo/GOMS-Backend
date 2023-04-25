package com.project.goms.domain.outing.common.exception

import com.project.goms.global.error.ErrorCode
import com.project.goms.global.error.exception.GomsException

class OutingAccountNotFoundException: GomsException(ErrorCode.OUTING_ACCOUNT_NOT_FOUND)