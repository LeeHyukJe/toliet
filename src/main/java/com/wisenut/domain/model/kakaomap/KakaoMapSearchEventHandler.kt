package com.wisenut.domain.model.kakaomap

import com.wisenut.domain.model.kakaomap.events.KakaoMapSearchEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component


@Component
class KakaoMapSearchEventHandler {
    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.simpleName)
    }
    @EventListener(KakaoMapSearchEvent::class)
    fun handleEvent(searchEvent: KakaoMapSearchEvent) {
        log.info("카카오맵 검색 함..." + searchEvent.kaKaoMapOffer.searchList)
    }
}