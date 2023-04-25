package com.project.goms.domain.outing.common.exception

import com.project.goms.global.error.ErrorCode
import com.project.goms.global.error.exception.GomsException

class BlackListNotAllowOutingException: GomsException(ErrorCode.BLACKLIST_NOT_ALLOW_OUTING)