package com.project.goms.global.error

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val message: String,
    val status: HttpStatus
) {

    // ACCOUNT
    ACCOUNT_NOT_FOUND("계정을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

    // TOKEN
    INVALID_TOKEN("유효하지 않은 토큰입니다.", HttpStatus.UNAUTHORIZED),
    EXPIRED_ACCESS_TOKEN("만료된 accessToken 입니다.", HttpStatus.UNAUTHORIZED),
    EXPIRED_REFRESH_TOKEN("만료된 refreshToken 입니다.", HttpStatus.UNAUTHORIZED),

}