package com.project.goms.domain.auth.persistence.repository

import com.project.goms.domain.auth.persistence.entity.RefreshToken
import org.springframework.data.repository.CrudRepository

interface RefreshTokenRepository: CrudRepository<RefreshToken, String>