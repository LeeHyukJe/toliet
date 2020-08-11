package com.wisenut.domain;

import java.util.List;

public interface ToiletStationService {

    List<String> readToiletStation();

    List<? extends IMapInfo> search(String stationName);
}
