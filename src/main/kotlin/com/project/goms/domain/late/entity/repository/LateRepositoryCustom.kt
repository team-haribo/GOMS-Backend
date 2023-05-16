package com.project.goms.domain.late.entity.repository

import com.project.goms.domain.late.usecase.dto.LateRankDto

interface LateRepositoryCustom {

    fun findTop5ByOrderByAccountDesc(): List<LateRankDto>

}