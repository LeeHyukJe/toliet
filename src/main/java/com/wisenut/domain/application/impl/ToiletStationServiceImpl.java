package com.wisenut.domain.application.impl;

import com.wisenut.domain.application.ToiletStationService;
import com.wisenut.domain.model.IMapInfo;
import com.wisenut.domain.model.IMapOffer;
import com.wisenut.domain.model.IUser;
import com.wisenut.domain.model.kakaomap.KaKaoMapInfo;
import com.wisenut.domain.model.kakaomap.KaKaoMapInfoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class ToiletStationServiceImpl implements ToiletStationService {

    private IUser iUser;
    private IMapOffer iMapOffer;
    private KaKaoMapInfoRepository kaKaoMapInfoRepository;

    public ToiletStationServiceImpl(IUser iUser, IMapOffer iMapOffer,
                                    KaKaoMapInfoRepository kaKaoMapInfoRepository){
        this.iUser = iUser;
        this.iMapOffer = iMapOffer;
        this.kaKaoMapInfoRepository = kaKaoMapInfoRepository;
    }

    /**
     * DB insert
     */
    @Override
    public void createToiletStation() {
        List<String> stationNames = iMapOffer.collectMapInfo();
        List<KaKaoMapInfo> list = new ArrayList<>();
        for(String name: stationNames){
            //List<? extends IMapInfo> iMapInfo = search(name).get(0);
            //list.add((KaKaoMapInfo) search(name).get(0));
            kaKaoMapInfoRepository.save((KaKaoMapInfo) search(name).get(0));
        }
        //kaKaoMapInfoRepository.saveAll(list);
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
