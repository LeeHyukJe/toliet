package com.wisenut.toliet;

import com.wisenut.domain.application.ToiletStationService;
import com.wisenut.domain.model.kakaomap.KaKaoMapInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class ToiletApplicationTests {

    @Autowired
    private ToiletStationService toiletStationService;
    @Autowired
    private KaKaoMapInfoRepository kaKaoMapInfoRepository;

    @Autowired
    private MockMvc mockMVc;

    @Test
    public void integrated_test_should_success() throws Exception {
        mockMVc.perform(MockMvcRequestBuilders.get("/api/calculation/distance")
                .param("stationName","회현역"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}
