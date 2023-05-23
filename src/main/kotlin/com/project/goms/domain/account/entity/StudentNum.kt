package com.project.goms.domain.account.entity

import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.MappedSuperclass

@Embeddable
@MappedSuperclass
class StudentNum(
    @Column(nullable = false, length = 1)
    val grade: Int,

    @Column(nullable = false, length = 1)
    val classNum: Int,

    @Column(nullable = false, length = 2)
    val number: Int
)