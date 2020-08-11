package com.wisenut.domain.impl;

import com.wisenut.domain.IMapInfo;
import com.wisenut.domain.IMapOffer;
import com.wisenut.domain.IUser;
import com.wisenut.domain.ToiletStationService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ToiletStationServiceImpl implements ToiletStationService {

    private IUser iUser;
    private IMapOffer iMapOffer;

    public ToiletStationServiceImpl(IUser iUser){
        this.iUser = iUser;
    }

    @Override
    public List<String> readToiletStation() {
        iMapOffer.collectMapInfo();
        return null;
    }

    @Override
    public List<? extends IMapInfo> search(String stationName) {
        IMapOffer iMapOffer = iUser.search(stationName);
        List<? extends IMapInfo> iMapInfos = iMapOffer.searchStation(stationName);
        return iMapInfos;
    }
}
