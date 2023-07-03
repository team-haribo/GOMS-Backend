package com.project.goms.domain.account.entity

import com.project.goms.global.entity.BaseUUIDEntity
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity(name = "account")
class Account(
    @Column(name = "account_idx")
    override val idx: UUID,

    @Column(nullable = false, length = 40)
    val email: String,

    @Embedded
    val studentNum: StudentNum,

    @Column(nullable = false, length = 10)
    val name: String,

    @Column(nullable = true, columnDefinition = "TEXT")
    val profileUrl: String?,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var authority: Authority,

    @Column(nullable = false, updatable = false, columnDefinition = "DATETIME(6)")
    val createdTime: LocalDateTime = LocalDateTime.now()
): BaseUUIDEntity(idx)

fun Account.updateAuthority(newAuthority: Authority) {
    authority = newAuthority
}
