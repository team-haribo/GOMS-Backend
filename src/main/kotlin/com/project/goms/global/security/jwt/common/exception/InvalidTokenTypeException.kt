package com.project.goms.global.security.jwt.common.exception

import com.project.goms.global.error.ErrorCode
import com.project.goms.global.error.exception.GomsException

class InvalidTokenTypeException: GomsException(ErrorCode.INVALID_TOKEN_TYPE) {
}