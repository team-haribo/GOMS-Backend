package com.project.goms.domain.late.entity.repository

import com.project.goms.domain.account.usecase.dto.StudentNumberDto
import com.project.goms.domain.late.entity.QLate.late
import com.project.goms.domain.late.usecase.dto.LateRankDto
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import com.querydsl.core.types.Projections

@Repository
class CustomLateRepository(
    private val queryFactory: JPAQueryFactory
) {

    fun findTop5ByOrderByAccountDesc(): List<LateRankDto> =
        queryFactory.from(late)
            .select(
                Projections.constructor(
                    LateRankDto::class.java,
                    late.account.idx,
                    late.account.name,
                    Projections.constructor(
                        StudentNumberDto::class.java,
                        late.account.studentNum.grade,
                        late.account.studentNum.classNum,
                        late.account.studentNum.number
                    ),
                    late.account.profileUrl
                )
            )
            .groupBy(late.account.idx)
            .orderBy(late.account.idx.count().desc())
            .limit(5)
            .fetch()

}