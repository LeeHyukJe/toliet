package com.wisenut.domain.impl;

import com.wisenut.domain.ToiletStationService;
import com.wisenut.domain.common.ToiletStationManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToiletStationServiceImpl implements ToiletStationService {

    private ToiletStationManager toiletStationManager;

    public ToiletStationServiceImpl(ToiletStationManager toiletStationManager){
        this.toiletStationManager = toiletStationManager;
    }
    @Override
    public List<String> readToiletStation() {
        List<String> result = toiletStationManager.readToiletStation();
        return result;
    }
}
