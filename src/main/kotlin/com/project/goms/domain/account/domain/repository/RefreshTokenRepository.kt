package com.project.goms.domain.account.domain.repository

import com.project.goms.domain.account.domain.RefreshToken
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface RefreshTokenRepository: CrudRepository<RefreshToken, UUID>