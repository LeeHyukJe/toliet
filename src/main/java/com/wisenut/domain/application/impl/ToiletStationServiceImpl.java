package com.wisenut.domain.application.impl;

import com.wisenut.domain.application.ToiletStationService;
import com.wisenut.domain.application.commands.SearchCommand;
import com.wisenut.domain.model.IMapInfo;
import com.wisenut.domain.model.IMapOffer;
import com.wisenut.domain.model.IUser;
import com.wisenut.domain.model.kakaomap.KaKaoMapInfo;
import com.wisenut.domain.model.kakaomap.KaKaoMapOffer;
import com.wisenut.domain.model.kakaomap.KakaoMapSearch;
import com.wisenut.domain.model.kakaomap.KaKaoMapInfoRepository;
import com.wisenut.domain.model.kakaomap.KakaoMapOfferRepository;
import com.wisenut.domain.model.user.User;
import com.wisenut.domain.model.user.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@Transactional
public class ToiletStationServiceImpl implements ToiletStationService {

    private User user;
    private IMapOffer iMapOffer;
    private KaKaoMapInfoRepository kaKaoMapInfoRepository;
    private KakaoMapOfferRepository kakaoMapOfferRepository;
    public ToiletStationServiceImpl(User user
                                    , IMapOffer iMapOffer
                                    , KaKaoMapInfoRepository kaKaoMapInfoRepository
                                    , KakaoMapOfferRepository kakaoMapOfferRepository){
        this.user = user;
        this.iMapOffer = iMapOffer;
        this.kaKaoMapInfoRepository = kaKaoMapInfoRepository;
        this.kakaoMapOfferRepository = kakaoMapOfferRepository;
    }

    /**
     * DB insert
     */
    @Override
    public void createToiletStation() {
        List<String> stationNames = iMapOffer.collectMapInfo();
        List<KaKaoMapInfo> list = new ArrayList<>();
        for(String name: stationNames){
            SearchCommand command = SearchCommand.builder().stationName(name).build();
            list.add((KaKaoMapInfo) search(command).get(0));
        }
        log.info("@@@[저장될 역 리스트들.....]"+list.toString());
        kaKaoMapInfoRepository.saveAll(list);

        KaKaoMapOffer kaKaoMapOffer = KaKaoMapOffer
                .builder()
                .type("kakao")
                .build();
        kakaoMapOfferRepository.save(kaKaoMapOffer);

    }

    @Override
    public List<? extends IMapInfo> search(SearchCommand command) {
        IMapOffer iMapOffer = user.search(command.getStationName());
        List<? extends IMapInfo> iMapInfos = iMapOffer.searchStation(command.getStationName());
        return iMapInfos;
    }

    @Transactional
    @Override
    public String searchNearestStationName(SearchCommand command){
        // DB에서 해당 역 정보들 모두 가져오기
        //createToiletStation();
        List<KaKaoMapInfo> kaKaoMapInfos = kaKaoMapInfoRepository.findAll();

        // DB와 현재 위치 좌표를 계산해서 가장 가까운 역 찾기 (두 점 사이의 거리 계산)
        String result = iMapOffer.calculateDistance(kaKaoMapInfos, command.getX(), command.getY());

        KakaoMapSearch newKakaoSearch = KakaoMapSearch.builder()
                .createdDate(new Date())
                .userid(command.getUserId())
                .build();

        KaKaoMapOffer kaKaoMapOffer = kakaoMapOfferRepository.findByType("kakao");
        // null 처리 해야 함
         List<KakaoMapSearch> searchList = kaKaoMapOffer.getSearchList();
        if(searchList == null){
            searchList = new ArrayList<>();
            searchList.add(newKakaoSearch);
        }
        kaKaoMapOffer.update(kaKaoMapInfos, searchList);
        kakaoMapOfferRepository.save(kaKaoMapOffer);


        return result;
    }

    @Override
    public IMapInfo searchToiletStation(SearchCommand command){
        KaKaoMapInfo kaKaoMapInfo = kaKaoMapInfoRepository.findById(1l).orElse(null);
        return kaKaoMapInfo;
    }
}
