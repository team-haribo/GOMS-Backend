package com.project.goms.domain.auth.persistence

import com.project.goms.domain.auth.RefreshToken
import org.springframework.data.repository.CrudRepository

interface RefreshTokenRepository: CrudRepository<RefreshToken, String>