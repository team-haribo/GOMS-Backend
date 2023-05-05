package com.project.goms.global.entity

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseTimeEntity(
    @Column(nullable = false, updatable = false, columnDefinition = "DATETIME(6)")
    val createdTime: LocalDateTime = LocalDateTime.now()
)