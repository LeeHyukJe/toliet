package com.wisenut.domain.model.kakaomap;

import com.wisenut.domain.model.kakaomap.events.KakaoMapSearchEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class KakaoMapSearchEventHandler {

    @EventListener(KakaoMapSearchEvent.class)
    public void handleEvent(KakaoMapSearchEvent searchEvent) {
       log.info("카카오맵 검색 함..."+searchEvent.getKaKaoMapOffer().getSearchList());
    }
}
