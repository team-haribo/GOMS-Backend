package com.project.goms.domain.outing.entity

import com.project.goms.domain.account.entity.Account
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "outing")
class Outing(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "outing_idx")
    val idx: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_idx")
    val account: Account,

    @Column(nullable = false, updatable = false, columnDefinition = "DATETIME(6)")
    val createdTime: LocalDateTime = LocalDateTime.now()
)