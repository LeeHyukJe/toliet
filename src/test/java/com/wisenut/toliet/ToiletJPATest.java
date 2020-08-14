package com.wisenut.toliet;

import com.wisenut.domain.model.kakaomap.KaKaoMapInfo;
import com.wisenut.domain.model.kakaomap.KaKaoMapInfoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ToiletJPATest {

    @Autowired
    private KaKaoMapInfoRepository kaKaoMapInfoRepository;


    @Test
    public void db_insert_test_should_success(){
//        KaKaoMapInfo kaKaoMapInfo = KaKaoMapInfo.builder().id(1l).category_group_code("SW8").category_group_name("지하철역")
//                .category_name("교통,수송 > 지하철,전철 > 수도권4호선").phone("02-6110-4251").place_name("회현역 4호선")
//                .place_url("http://place.map.kakao.com/21160642").road_address_name("서울 중구 퇴계로 지하 54")
//                .address_name("서울 중구 남창동 116-1")
//                .x("126.97843725718793")
//                .y("37.558760244882336").build();
//
//        kaKaoMapInfoRepository.save(kaKaoMapInfo);
//
//        KaKaoMapInfo info = kaKaoMapInfoRepository.findById(1l).get();
//        Assert.assertEquals(info.getAddress_name(),"서울 중구 남창동 116-1");
    }
}
