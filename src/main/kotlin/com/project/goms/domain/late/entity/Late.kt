package com.project.goms.domain.late.entity

import com.project.goms.domain.account.entity.Account
import java.time.LocalDate
import javax.persistence.*

@Entity(name = "late")
class Late(
    @Id
    @Column(name = "late_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idx: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_idx")
    val account: Account,

    @Column(nullable = false, updatable = false)
    val createdTime: LocalDate = LocalDate.now()
)