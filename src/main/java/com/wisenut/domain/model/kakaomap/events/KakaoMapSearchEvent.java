package com.wisenut.domain.model.kakaomap.events;

import com.wisenut.domain.common.event.DomainEvent;
import com.wisenut.domain.model.kakaomap.KaKaoMapOffer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
public class KakaoMapSearchEvent extends DomainEvent {
    private KaKaoMapOffer kaKaoMapOffer;

    public KakaoMapSearchEvent(KaKaoMapOffer kaKaoMapOffer) {
        super(kaKaoMapOffer);
        this.kaKaoMapOffer = kaKaoMapOffer;
    }

}
