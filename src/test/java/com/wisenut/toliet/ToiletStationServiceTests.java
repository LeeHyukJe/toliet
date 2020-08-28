package com.wisenut.toliet;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wisenut.domain.application.ToiletStationService;
import com.wisenut.domain.model.IMapInfo;
import com.wisenut.domain.model.kakaomap.KaKaoMapInfo;
import com.wisenut.domain.model.kakaomap.KaKaoMapInfoRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.wisenut.domain.model.kakaomap.QKaKaoMapInfo.kaKaoMapInfo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToiletStationServiceTests {

    @Autowired
    private ToiletStationService toiletStationService;
    @Autowired
    private KaKaoMapInfoRepository kaKaoMapInfoRepository;
    @Autowired
    private JPAQueryFactory queryFactory;

    @Before
    public void init(){
        KaKaoMapInfo kaKaoMapInfo = KaKaoMapInfo.builder().id(21160642l).categoryGroupCode("SW8").categoryGroupName("지하철역")
                .categoryName("교통,수송 > 지하철,전철 > 수도권4호선").phone("02-6110-4251").placeName("회현역 4호선")
                .placeUrl("http://place.map.kakao.com/21160642").roadAddressName("서울 중구 퇴계로 지하 54")
                .addressName("서울 중구 남창동 116-1")
                .x("126.97843725718793")
                .y("37.558760244882336").build();

        kaKaoMapInfoRepository.save(kaKaoMapInfo);
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
    public void test(){
        String[] list = {"홍제동 홍제역, 무악동 무악재역, 창천동 독립문역"};
        String test = "홍제역";

        for(String str : list){
            if(str.contains(test)){
                System.out.println("포함됨!!");
                break;
            }
        }
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
}
