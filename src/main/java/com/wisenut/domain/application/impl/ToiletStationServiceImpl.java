package com.wisenut.domain.application.impl;

import com.wisenut.domain.application.ToiletStationService;
import com.wisenut.domain.application.commands.SearchCommand;
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
import java.util.Optional;


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
            SearchCommand command = new SearchCommand(name,"","","","");
            list.add((KaKaoMapInfo) search(command).get(0));
        }
        kaKaoMapInfoRepository.saveAll(list);
    }

    @Override
    public List<? extends IMapInfo> search(SearchCommand command) {
        IMapOffer iMapOffer = iUser.search(command.getStationName());
        List<? extends IMapInfo> iMapInfos = iMapOffer.searchStation(command.getStationName());
        return iMapInfos;
    }

    @Override
    public String searchNearestStationName(SearchCommand command){
        // DB에서 해당 역 정보들 모두 가져오기
        //createToiletStation();
//        KaKaoMapInfo defaultObject = KaKaoMapInfo.builder().addressName("no_exist").build();
//        KaKaoMapInfo kaKaoMapInfo = kaKaoMapInfoRepository.findByPlaceNameContaining(stationName).orElse(defaultObject);
        List<KaKaoMapInfo> kaKaoMapInfos = kaKaoMapInfoRepository.findAll();

        // DB와 현재 위치 좌표를 계산해서 가장 가까운 역 찾기 (두 점 사이의 거리 계산)
        String result = iMapOffer.calculateDistance(kaKaoMapInfos, command.getX(), command.getY());
        return result;
    }

    @Override
    public IMapInfo searchToiletStation(SearchCommand command){
        KaKaoMapInfo kaKaoMapInfo = kaKaoMapInfoRepository.findById(1l).orElse(null);
        return kaKaoMapInfo;
    }
}
