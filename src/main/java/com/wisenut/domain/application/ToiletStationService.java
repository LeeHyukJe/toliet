package com.wisenut.domain.application;

import com.wisenut.domain.application.commands.SearchCommand;
import com.wisenut.domain.model.IMapInfo;

import java.util.List;
import java.util.Map;

public interface ToiletStationService {

    void createToiletStation();

    List<? extends IMapInfo> search(SearchCommand command);
    String searchNearestStationName (SearchCommand command);
    IMapInfo searchToiletStation(SearchCommand command);
}
