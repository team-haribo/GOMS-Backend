package com.project.goms.domain.account.entity

import com.project.goms.domain.auth.presentation.data.enums.Authority
import com.project.goms.global.entity.BaseUUIDEntity
import java.util.*
import javax.persistence.*

@Entity(name = "account")
class Account(
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
    val name: String,

    @Column(nullable = true, columnDefinition = "TEXT")
    val profileUrl: String?,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var authority: Authority
): BaseUUIDEntity(idx)

fun Account.updateAuthority(newAuthority: Authority) {
    authority = newAuthority
}
