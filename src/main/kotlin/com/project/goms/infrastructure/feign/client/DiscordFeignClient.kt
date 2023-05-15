package com.project.goms.infrastructure.feign.client

import com.project.goms.infrastructure.discord.usecase.dto.DiscordDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "discordFeignClient", url = "\${discord.webhookUrl}")
interface DiscordFeignClient {

    @PostMapping(headers = ["Content-Type: application/x-www-form-urlencoded"])
    fun sendDiscord(@RequestBody discordDto: DiscordDto)

}