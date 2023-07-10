package com.project.goms.global.config

import com.project.goms.global.annotation.UseCase
import com.project.goms.global.annotation.UseCaseWithReadOnlyTransaction
import com.project.goms.global.annotation.UseCaseWithTransaction
import org.springframework.context.annotation.ComponentScan.Filter
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType
import org.springframework.stereotype.Component

@Configuration
@ComponentScan(
    basePackages = ["com.project.goms"],
    includeFilters = [
        Filter(
            type = FilterType.ANNOTATION,
            classes = [
                UseCaseWithTransaction::class,
                UseCaseWithReadOnlyTransaction::class,
                UseCase::class
            ]
        )
    ]
)
class ComponentScanConfig