package com.wisenut.domain.application.impl;

import com.wisenut.domain.application.ToiletStationService;
import com.wisenut.domain.application.commands.SearchCommand;
import com.wisenut.domain.model.IMapInfo;
import com.wisenut.domain.model.IMapOffer;
import com.wisenut.domain.model.IMapSearch;
import com.wisenut.domain.model.IUser;
import com.wisenut.domain.model.kakaomap.KaKaoMapInfo;
import com.wisenut.domain.model.kakaomap.KaKaoMapOffer;
import com.wisenut.domain.model.kakaomap.KakaoMapSearch;
import com.wisenut.domain.model.kakaomap.KakaoMapUser;
import com.wisenut.infrastructures.repository.kakao.KaKaoMapInfoRepository;
import com.wisenut.infrastructures.repository.kakao.KaKaoMapUserRespository;
import com.wisenut.infrastructures.repository.kakao.KakaoMapOfferRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@Transactional
public class ToiletStationServiceImpl implements ToiletStationService {

    private IUser iUser;
    private IMapOffer iMapOffer;
    private KaKaoMapInfoRepository kaKaoMapInfoRepository;
    private KakaoMapOfferRepository kakaoMapOfferRepository;
    private KaKaoMapUserRespository kaKaoMapUserRespository;

    public ToiletStationServiceImpl(IUser iUser, IMapOffer iMapOffer,
                                    KaKaoMapInfoRepository kaKaoMapInfoRepository
                                    ,KakaoMapOfferRepository kakaoMapOfferRepository
                                    ,KaKaoMapUserRespository kaKaoMapUserRespository){
        this.iUser = iUser;
        this.iMapOffer = iMapOffer;
        this.kaKaoMapInfoRepository = kaKaoMapInfoRepository;
        this.kakaoMapOfferRepository = kakaoMapOfferRepository;
        this.kaKaoMapUserRespository = kaKaoMapUserRespository;
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
        IMapOffer iMapOffer = iUser.search(command.getStationName());
        List<? extends IMapInfo> iMapInfos = iMapOffer.searchStation(command.getStationName());
        return iMapInfos;
    }

    @Transactional
    @Override
    public String searchNearestStationName(SearchCommand command){
        // DB에서 해당 역 정보들 모두 가져오기
        createToiletStation();
        List<KaKaoMapInfo> kaKaoMapInfos = kaKaoMapInfoRepository.findAll();


        // DB와 현재 위치 좌표를 계산해서 가장 가까운 역 찾기 (두 점 사이의 거리 계산)
        String result = iMapOffer.calculateDistance(kaKaoMapInfos, command.getX(), command.getY());


        // User 가져오기 (원래대로라면 command객체에서 가져와야 함)
        KakaoMapUser kakaoMapUser = kaKaoMapUserRespository.findById(1l).get();

        KakaoMapSearch newKakaoSearch = KakaoMapSearch.builder()
                .createdDate(new Date())
                .userid(kakaoMapUser.getId())
                .build();

        KaKaoMapOffer kaKaoMapOffer = kakaoMapOfferRepository.findByType("kakao");
        // null 처리 해야 함
        List<KakaoMapSearch> searchList = kaKaoMapOffer.getSearchList();
        searchList.add(newKakaoSearch);
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
