package com.wisenut.config;

import com.wisenut.domain.application.ToiletStationService;
import com.wisenut.domain.model.IMapOffer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleCollectStationName {

    private ToiletStationService toiletStationService;
    public ScheduleCollectStationName(ToiletStationService toiletStationService){
        this.toiletStationService = toiletStationService;
    }

    @Scheduled(cron = "0 0/30 * * * ?")
    public void collectToiletStationName(){
        toiletStationService.createToiletStation();
    }
}
