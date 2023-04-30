package com.project.goms.domain.outing.entity

import com.project.goms.domain.account.entity.Account
import com.project.goms.global.entity.BaseIdxEntity
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tbl_outing")
class Outing(
    @Column(name = "outing_idx")
    override val idx: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    val account: Account,

    @CreatedDate
    @Column(nullable = false)
    val createdTime: LocalDateTime
): BaseIdxEntity(idx)