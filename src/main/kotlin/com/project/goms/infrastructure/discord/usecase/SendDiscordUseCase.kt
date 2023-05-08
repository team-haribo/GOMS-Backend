package com.project.goms.infrastructure.discord.usecase

import com.project.goms.domain.late.entity.repository.LateRepository
import com.project.goms.domain.outing.usecase.SendMessageUseCase
import com.project.goms.infrastructure.discord.usecase.dto.DiscordDto
import com.project.goms.infrastructure.feign.client.DiscordFeignClient
import mu.KotlinLogging
import org.springframework.stereotype.Component
import java.time.LocalDate

private val log = KotlinLogging.logger {}

@Component
class SendDiscordUseCase(
    private val discordFeignClient: DiscordFeignClient,
    private val lateRepository: LateRepository
): SendMessageUseCase {

    override fun sendMessage() {
        var content = ""
        val lateOneWeekAgoCount = lateRepository.lateCountOntWeekAgo(LocalDate.now().minusWeeks(1))

        log.info { "one week ago late count is $lateOneWeekAgoCount" }

        when (lateOneWeekAgoCount < 3) {
            true -> {
                content = "@everyone \n"
                content += "\uD83D\uDCE2 오늘은 수요일 입니다. \uD83D\uDCE2 \n"
                content += "> 금일 수요외출제를 통해 외출을 할 학생들은 반드시 저녁을 먹고 7시부터 자유롭게 외출 해주시고, \n"
                content += "> **꼭 나가기 전 후로 GOMS 앱을 통해 QR을 찍어주세요!**\n"
                content += "> 7시 30분까지 각 반 자리에 앉아 야자감독 선생님께 출석확인을 받은 뒤 이동해주세요.\n"
                content += "> 외출 시 꼭 운동화 착용 부탁드립니다!\n"
            }

            false -> {
                content = "@everyone \n"
                content += "\uD83D\uDCE2 오늘은 수요일 입니다. \uD83D\uDCE2 \n"
                content += "> 저번주 외출제 지각생이 ${lateOneWeekAgoCount}명이여서 외출제 없습니다. \n"
                content += "> **오늘 나가면 무단 외출입니다** 😁\n"
            }
        }

        discordFeignClient.sendDiscord(DiscordDto(content = content))
    }

}