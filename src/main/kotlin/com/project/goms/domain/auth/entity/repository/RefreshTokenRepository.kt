package com.project.goms.domain.auth.entity.repository

import com.project.goms.domain.auth.entity.RefreshToken
import org.springframework.data.repository.CrudRepository

interface RefreshTokenRepository: CrudRepository<RefreshToken, String>