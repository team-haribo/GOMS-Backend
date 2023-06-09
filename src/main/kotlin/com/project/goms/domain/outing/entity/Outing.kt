package com.project.goms.domain.outing.entity

import com.project.goms.domain.account.entity.Account
import com.project.goms.global.entity.BaseIdxEntity
import java.time.LocalTime
import javax.persistence.*

@Entity(name = "outing")
class Outing(
    @Column(name = "outing_idx")
    override val idx: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_idx")
    val account: Account,

    @Column(nullable = false, updatable = false)
    val createdTime: LocalTime = LocalTime.now()
): BaseIdxEntity(idx)