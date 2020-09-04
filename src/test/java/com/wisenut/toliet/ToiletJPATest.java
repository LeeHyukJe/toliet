package com.wisenut.toliet;

import com.wisenut.domain.model.kakaomap.KaKaoMapInfo;
import com.wisenut.infrastructures.repository.kakao.KaKaoMapInfoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ToiletJPATest {

    @Autowired
    private KaKaoMapInfoRepository kaKaoMapInfoRepository;


    @Test
    public void db_insert_test_should_success(){
        KaKaoMapInfo kaKaoMapInfo = KaKaoMapInfo.builder().id(21160642l).categoryGroupCode("SW8").categoryGroupName("지하철역")
                .categoryName("교통,수송 > 지하철,전철 > 수도권4호선").phone("02-6110-4251").placeName("회현역 4호선")
                .placeUrl("http://place.map.kakao.com/21160642").roadAddressName("서울 중구 퇴계로 지하 54")
                .addressName("서울 중구 남창동 116-1")
                .x("126.97843725718793")
                .y("37.558760244882336").build();


        kaKaoMapInfoRepository.save(kaKaoMapInfo);

        KaKaoMapInfo info = kaKaoMapInfoRepository.findById(21160642l).get();
        Assert.assertEquals(info.getAddressName(),"서울 중구 남창동 116-1");
    }

    @Test
    public void test3(){
        List<KaKaoMapInfo> list = kaKaoMapInfoRepository.findAll();

        KaKaoMapInfo kaKaoMapInfo = KaKaoMapInfo.builder().id(21160642l).categoryGroupCode("SW8").categoryGroupName("지하철역")
                .categoryName("교통,수송 > 지하철,전철 > 수도권4호선").phone("02-6110-4251").placeName("회현역 4호선")
                .placeUrl("http://place.map.kakao.com/21160642").roadAddressName("서울 중구 퇴계로 지하 54")
                .addressName("서울 중구 남창동 116-1")
                .x("126.97843725718793")
                .y("37.558760244882336").build();

        list = list.stream().filter(ele -> !kaKaoMapInfoRepository.existsById(ele.getId())).collect(Collectors.toList());
        Assert.assertEquals(list.size(), 0);

    }
}
