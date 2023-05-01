package com.project.goms.domain.auth.common.exception

import com.project.goms.global.error.ErrorCode
import com.project.goms.global.error.exception.GomsException

class GAuthException: GomsException(ErrorCode.GAUTH_SERVER_ERROR)