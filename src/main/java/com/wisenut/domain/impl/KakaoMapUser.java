package com.wisenut.domain.impl;

import com.wisenut.domain.IMapOffer;
import com.wisenut.domain.IUser;
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
