package com.project.goms.global.health

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckController {

    @GetMapping
    fun healthCheck(): ResponseEntity<Void> =
        ResponseEntity.ok().build()

}