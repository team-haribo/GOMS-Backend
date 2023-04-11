package com.project.goms.domain.account.domain

import com.project.goms.domain.account.presentation.data.enums.Authority
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
class Account(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "account_idx", columnDefinition = "BINARY(16)", nullable = false)
    val idx: UUID,

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
)
