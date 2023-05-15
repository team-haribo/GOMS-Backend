package com.project.goms.infrastructure.feign.error.exception

import com.project.goms.global.error.ErrorCode
import com.project.goms.global.error.exception.GomsException

class FeignBadRequestException: GomsException(ErrorCode.FEIGN_BAD_REQUEST)