package com.wisenut.domain.application;

import com.wisenut.domain.model.IMapInfo;

import java.util.List;
import java.util.Map;

public interface ToiletStationService {

    void createToiletStation();

    List<? extends IMapInfo> search(String stationName);
    IMapInfo calculateDistance (Map<String,Object> params);
}
