package com.wisenut.domain.model.kakaomap;

import com.wisenut.domain.model.IMapOffer;
import com.wisenut.domain.model.IUser;
import org.springframework.stereotype.Component;

@Component
public class KakaoMapUser implements IUser {
    private IMapOffer iMapOffer;

    public KakaoMapUser(IMapOffer iMapOffer){
        this.iMapOffer = iMapOffer;
    }
    @Override
    public IMapOffer search(String stationName) {
        iMapOffer.searchStation(stationName);
        return iMapOffer;
    }
}
