package com.wisenut.toliet.web.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wisenut.domain.application.ToiletStationService;
import com.wisenut.domain.model.kakaomap.KaKaoMapInfo;
import com.wisenut.web.apis.ToiletSearchAPIController;
import com.wisenut.web.payload.SearchPayload;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ToiletSearchAPIController.class)
class ToiletSearchAPIControllerTests {

    @MockBean
    private ToiletStationService toiletStationService;

    @Autowired
    private MockMvc mockMVc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void toilet_search_api_test_should_success() throws Exception {
        SearchPayload payload = new SearchPayload();
        payload.setStationName("가락시장역");

        KaKaoMapInfo kaKaoMapInfo = KaKaoMapInfo.builder().addressName("서울 중구 남창동 116-1").placeName("회현역 4호선").x("126.97843725718793").y("37.558760244882336").build();
        String content = objectMapper.writeValueAsString(kaKaoMapInfo);

        given(toiletStationService.searchNearestStationName(payload.toCommand())).willReturn("회현역");

        final ResultActions result = mockMVc.perform(post("/api/calculation/distance")
        .content(content)
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
