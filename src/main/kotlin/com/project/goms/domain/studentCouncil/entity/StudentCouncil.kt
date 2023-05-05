package com.project.goms.domain.studentCouncil.entity

import com.project.goms.domain.account.entity.Account
import com.project.goms.global.entity.BaseUUIDEntity
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.OneToOne

@Entity(name = "student_council")
class StudentCouncil(
    @Column(name = "student_council_idx")
    override val idx: UUID,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_idx")
    val account: Account,
): BaseUUIDEntity(idx)