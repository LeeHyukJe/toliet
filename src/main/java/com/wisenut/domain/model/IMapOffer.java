package com.wisenut.domain.model;

import java.util.List;

public interface IMapOffer {
    List<String> collectMapInfo();

    // 카카오 API로부터 해당 역의 정보를 얻어오기
    void getToiletInfoByStationNameFromAPI(String stationName);
    // 자신의 현재 위치로부터 근처 역 위치 정보 가져오기
    void getToiletInfoByOwnPositionFromAPI(String[] stationNames);
    // 역 명으로 위치 정보 얻기
    List<? extends IMapInfo> searchStation(String stationName);
    // 역간 거리 계산하기
    String calculateDistance(List<? extends IMapInfo> iMapInfos, String x, String y);
}
