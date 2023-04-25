package com.project.goms.global.entity

import javax.persistence.*

@MappedSuperclass
abstract class BaseIdxEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val idx: Long
)