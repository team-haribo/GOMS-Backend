package com.project.goms.domain.studentCouncil.entity

import com.project.goms.domain.account.entity.Account
import com.project.goms.global.entity.BaseUUIDEntity
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity(name = "student_council")
class StudentCouncil(
    @Column(name = "student_council_idx")
    override val idx: UUID,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_idx")
    val account: Account,

    @Column(nullable = false, updatable = false)
    val createdTime: LocalDate = LocalDate.now()
): BaseUUIDEntity(idx)