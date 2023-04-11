package com.project.goms.global.error.response

import org.springframework.http.HttpStatus

data class ErrorResponse(
    val message: String,
    val status: HttpStatus
)