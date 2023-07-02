package com.project.goms.domain.account.entity

import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity(name = "account")
class Account(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "account_idx", columnDefinition = "BINARY(16)", nullable = false)
    val idx: UUID,

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
)

fun Account.updateAuthority(newAuthority: Authority) {
    authority = newAuthority
}
