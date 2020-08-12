package com.wisenut.domain.application;

import com.wisenut.domain.model.IMapInfo;
import com.wisenut.domain.model.IMapOffer;
import com.wisenut.domain.model.IUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ToiletStationServiceImpl implements ToiletStationService {

    private IUser iUser;
    private IMapOffer iMapOffer;

    public ToiletStationServiceImpl(IUser iUser, IMapOffer iMapOffer){
        this.iUser = iUser;
        this.iMapOffer = iMapOffer;
    }

    /**
     * DB insert
     */
    @Override
    public void createToiletStation() {
        List<String> stationNames = iMapOffer.collectMapInfo();
        for(String name: stationNames){
            List<? extends IMapInfo> iMapInfo = search(name);
        }
    }

    @Override
    public List<? extends IMapInfo> search(String stationName) {
        IMapOffer iMapOffer = iUser.search(stationName);
        List<? extends IMapInfo> iMapInfos = iMapOffer.searchStation(stationName);
        return iMapInfos;
    }

    @Override
    public IMapInfo calculateDistance(Map<String, Object> params){
        // DB에서 해당 역 정보들 모두 가져오기
        // DB와 현재 위치 좌표를 계산해서 가장 가까운 역 찾기 (두 점 사이의 거리 계산)
        return null;
    }
}
