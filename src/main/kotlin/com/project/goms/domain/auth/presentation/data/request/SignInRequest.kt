package com.project.goms.domain.auth.presentation.data.request

import org.jetbrains.annotations.NotNull

data class SignInRequest(
    @field:NotNull
    val code: String
)