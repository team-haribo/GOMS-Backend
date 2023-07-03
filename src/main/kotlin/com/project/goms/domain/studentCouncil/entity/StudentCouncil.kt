package com.project.goms.domain.studentCouncil.entity

import com.project.goms.domain.account.entity.Account
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity(name = "student_council")
class StudentCouncil(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "student_council_idx", columnDefinition = "BINARY(16)", nullable = false)
    val idx: UUID,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_idx")
    val account: Account,

    @Column(nullable = false, updatable = false)
    val createdTime: LocalDate = LocalDate.now()
)