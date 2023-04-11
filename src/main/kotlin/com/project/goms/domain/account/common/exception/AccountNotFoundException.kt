package com.project.goms.domain.account.common.exception

import com.project.goms.global.error.ErrorCode
import com.project.goms.global.error.exception.GomsException

class AccountNotFoundException: GomsException(ErrorCode.ACCOUNT_NOT_FOUND)