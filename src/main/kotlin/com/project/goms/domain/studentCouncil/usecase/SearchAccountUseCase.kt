package com.project.goms.domain.studentCouncil.usecase

import com.project.goms.domain.account.entity.Authority
import com.project.goms.domain.account.entity.repository.CustomAccountRepository
import com.project.goms.domain.account.usecase.dto.StudentNumberDto
import com.project.goms.domain.outing.entity.repository.OutingBlackListRepository
import com.project.goms.domain.studentCouncil.usecase.dto.AllAccountDto
import com.project.goms.global.annotation.UseCaseWithReadOnlyTransaction
import kotlin.streams.asSequence

@UseCaseWithReadOnlyTransaction
class SearchAccountUseCase(
    private val customAccountRepository: CustomAccountRepository,
    private val outingBlackListRepository: OutingBlackListRepository
) {

    fun execute(grade: Int?, classNum: Int?, name: String?, authority: Authority?, isBlackList: Boolean?): List<AllAccountDto> {
        val outingBlacklistIds = outingBlackListRepository.findAll().filterNotNull().map { it.accountIdx }

        return customAccountRepository.findAccountByStudentInfo(grade, classNum, name, authority).stream().asSequence()
            .filter {
                if (isBlackList != null && isBlackList) outingBlacklistIds.contains(it.idx)
                else if(isBlackList != null && !isBlackList) outingBlacklistIds.contains(it.idx).not()
                else true
            }.map {
                AllAccountDto(
                    accountIdx = it.idx,
                    name = it.name,
                    studentNum = StudentNumberDto(it.studentNum.grade, it.studentNum.classNum, it.studentNum.number),
                    profileUrl = it.profileUrl,
                    authority = it.authority,
                    isBlackList = outingBlacklistIds.contains(it.idx)
                )
            }.toList()
    }
}