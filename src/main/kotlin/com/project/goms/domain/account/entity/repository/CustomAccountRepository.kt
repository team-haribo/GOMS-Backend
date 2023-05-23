package com.project.goms.domain.account.entity.repository

import com.project.goms.domain.account.entity.Account
import com.project.goms.domain.account.entity.Authority
import com.project.goms.domain.account.entity.QAccount.account
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class CustomAccountRepository(
    private val queryFactory: JPAQueryFactory
) {

    fun findAllOrderByStudentNum(): List<Account> =
        queryFactory.selectFrom(account)
            .orderBy(account.studentNum.grade.asc())
            .orderBy(account.studentNum.classNum.asc())
            .orderBy(account.studentNum.number.asc())
            .fetch()

    fun findAccountByStudentInfo(
        grade: Int?,
        classNum: Int?,
        name: String?,
        authority: Authority?,
    ): List<Account> =
        queryFactory
            .selectFrom(account)
            .where(
                eqGrade(grade),
                eqClassNum(classNum),
                likeName(name),
                eqAuthority(authority)
            )
            .orderBy(account.studentNum.grade.asc())
            .orderBy(account.studentNum.classNum.asc())
            .orderBy(account.studentNum.number.asc())
            .fetch()

    private fun eqGrade(grade: Int?): BooleanExpression? {
        if (grade == null) return null
        return account.studentNum.grade.eq(grade)
    }

    private fun eqClassNum(classNum: Int?): BooleanExpression? {
        if (classNum == null) return null
        return account.studentNum.classNum.eq(classNum)
    }

    private fun likeName(name: String?): BooleanExpression? {
        if (name.isNullOrEmpty()) return null
        return account.name.like("%$name%")
    }

    private fun eqAuthority(authority: Authority?): BooleanExpression? {
        if (authority == null) return null
        return account.authority.eq(authority)
    }

}