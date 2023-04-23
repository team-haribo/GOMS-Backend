package com.project.goms.global.annotation

import org.springframework.transaction.annotation.Transactional

@Target(AnnotationTarget.CLASS)
@Transactional(readOnly = true, rollbackFor = [Exception::class])
annotation class UseCaseWithReadOnlyTransaction
