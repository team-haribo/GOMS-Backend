package com.project.goms.domain.account.persistence.entity

import com.project.goms.domain.auth.presentation.data.enums.Authority
import com.project.goms.global.entity.BaseIdxEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tbl_account")
class Account(
    @Id
    @Column(name = "account_idx")
    override val idx: UUID,

    @Column(nullable = false, length = 40)
    val email: String,

    @Column(nullable = false, length = 1)
    val grade: Int,

    @Column(nullable = false, length = 1)
    val classNum: Int,

    @Column(nullable = false, length = 2)
    val number: Int,

    @Column(nullable = false, length = 10)
    var name: String,

    @Column(nullable = true, columnDefinition = "TEXT")
    var profileUrl: String?,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var authority: Authority
): BaseIdxEntity(idx)
