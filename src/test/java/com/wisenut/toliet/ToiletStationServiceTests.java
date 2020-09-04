package com.wisenut.toliet;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wisenut.domain.application.ToiletStationService;
import com.wisenut.domain.model.IMapOffer;
import com.wisenut.domain.model.kakaomap.*;
import com.wisenut.infrastructures.repository.kakao.KaKaoMapInfoRepository;
import com.wisenut.infrastructures.repository.kakao.KaKaoMapUserRespository;
import com.wisenut.infrastructures.repository.kakao.KakaoMapOfferRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.wisenut.domain.model.kakaomap.QKaKaoMapInfo.kaKaoMapInfo;
import static org.mockito.ArgumentMatchers.isNotNull;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest
public class ToiletStationServiceTests {

    @Autowired
    private ToiletStationService toiletStationService;
    @Autowired
    private KaKaoMapInfoRepository kaKaoMapInfoRepository;
    @Autowired
    private KakaoMapOfferRepository kakaoMapOfferRepository;
    @Autowired
    private KaKaoMapUserRespository kaKaoMapUserRespository;
    @Autowired
    private JPAQueryFactory queryFactory;

    @Before
    public void init(){
        KakaoMapUser user = KakaoMapUser.builder()
                .id(1l)
                .createdDate(new Date())
                .emailAddress("lims2733@naver.com")
                .firstName("lee")
                .lastName("hyukje")
                .password("1234")
                .username("이혁제")
                .build();


        KaKaoMapInfo kaKaoMapInfo = KaKaoMapInfo.builder().id(21160642l).categoryGroupCode("SW8").categoryGroupName("지하철역")
                .categoryName("교통,수송 > 지하철,전철 > 수도권4호선").phone("02-6110-4251").placeName("회현역 4호선")
                .placeUrl("http://place.map.kakao.com/21160642").roadAddressName("서울 중구 퇴계로 지하 54")
                .addressName("서울 중구 남창동 116-1")
                .x("126.97843725718793")
                .y("37.558760244882336").build();


//        KaKaoMapOffer kaKaoMapOffer = KaKaoMapOffer.builder()
//                .id(2l)
//                .type("kakamomap")
//                .build();


        kaKaoMapUserRespository.save(user);
        kaKaoMapInfoRepository.save(kaKaoMapInfo);
//        kakaoMapOfferRepository.save(kaKaoMapOffer);
    }

    @Test
    public void save_test_should_success(){
        //toiletStationService.createToiletStation();

//                KaKaoMapInfo kaKaoMapInfo = KaKaoMapInfo.builder().id(21160642l).category_group_code("SW8").category_group_name("지하철역")
//                .category_name("교통,수송 > 지하철,전철 > 수도권4호선").phone("02-6110-4251").place_name("회현역 4호선")
//                .place_url("http://place.map.kakao.com/21160642").road_address_name("서울 중구 퇴계로 지하 54")
//                .address_name("서울 중구 남창동 116-1")
//                .x("126.97843725718793")
//                .y("37.558760244882336").build();
//
//        kaKaoMapInfoRepository.save(kaKaoMapInfo);

//        IMapInfo kakaoMapInfo = kaKaoMapInfoRepository.findByAddress_name("서울 중구 남창동 116-1");
//        Assert.assertEquals(((KaKaoMapInfo)kakaoMapInfo).getAddress_name(),"서울");

    }


    @Test
    public void querydsl_test1(){
        // given
        String name = "회현역 4호선";

        // when
        List<KaKaoMapInfo> list = queryFactory.selectFrom(kaKaoMapInfo)
                .where(kaKaoMapInfo.placeName.contains("회현역"))
                .fetch();

        // then
        Assert.assertEquals(list.size(),1);
        Assert.assertEquals(list.get(0).getPlaceName(),name);

    }

    @Transactional
    @Test
    public void querydsl_test2(){
        // given
        KaKaoMapInfo kaKaoMapInfo = KaKaoMapInfo.builder().id(21160642l).categoryGroupCode("SW8").categoryGroupName("지하철역")
                .categoryName("교통,수송 > 지하철,전철 > 수도권4호선").phone("02-6110-4251").placeName("회현역 4호선")
                .placeUrl("http://place.map.kakao.com/21160642").roadAddressName("서울 중구 퇴계로 지하 54")
                .addressName("서울 중구 남창동 116-1")
                .x("126.97843725718793")
                .y("37.558760244882336").build();

        List<KaKaoMapInfo> list = new ArrayList<>();
        list.add(kaKaoMapInfo);

        KakaoMapSearch search = KakaoMapSearch.builder()
                .id(1l)
                .mapid(2l)
                .userid(1l)
                .createdDate(new Date())
                .build();

        List<KakaoMapSearch> list2 = new ArrayList<>();
        list2.add(search);

        KaKaoMapOffer kaKaoMapOffer = KaKaoMapOffer.builder()
                .documents(list)
                .searchList(list2)
                .id(1l)
                .build();


        kakaoMapOfferRepository.save(kaKaoMapOffer);

        // when
        List<KaKaoMapOffer> offerList = queryFactory.selectFrom(QKaKaoMapOffer.kaKaoMapOffer).fetch();
        KakaoMapSearch searchOne = queryFactory.selectFrom(QKakaoMapSearch.kakaoMapSearch)
                .where(QKakaoMapSearch.kakaoMapSearch.mapid.eq(2l))
                .fetchOne();

        // then
        Assert.assertEquals(offerList.size(),1);
        log.info("[@@@ 검색 이력...."+searchOne.toString());
        log.info("[@@@ 맵 리스트.....]"+offerList.toString());

    }

    @Test
    @Transactional
    public void 맵_제공자_한개_가져오기_성공(){
        IMapOffer kakaoMapOffer = kakaoMapOfferRepository.findMapById(1l);
        Assert.assertEquals(kakaoMapOffer,isNotNull());
//        log.info("[@@@ kakaoMapOffer...]"+kakaoMapOffer.toString());
    }
}
