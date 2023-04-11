package com.project.goms.global.error.exception

import com.project.goms.global.error.ErrorCode

open class GomsException(val errorCode: ErrorCode): RuntimeException(errorCode.message)