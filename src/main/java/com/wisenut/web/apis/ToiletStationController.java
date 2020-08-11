package com.wisenut.web.apis;

import com.wisenut.domain.IMapInfo;
import com.wisenut.domain.IMapOffer;
import com.wisenut.domain.ToiletStationService;
import com.wisenut.domain.impl.KakaoMapUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ToiletStationController {

    private ToiletStationService toiletStationService;

    public ToiletStationController(ToiletStationService toiletStationService){
        this.toiletStationService = toiletStationService;
    }

    @GetMapping("/api/toiletstations")
    public ResponseEntity<List<String>> selectToiletStation(){
        ResponseEntity<List<String>> entity;
        try{
            List<String> result = toiletStationService.readToiletStation();
            entity = new ResponseEntity<>(result, HttpStatus.OK);

            return entity;
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("api/search")
    public ResponseEntity<List<? extends IMapInfo>> searchToiletStation(String stationName){
        try{
            List<? extends IMapInfo> iMapInfos = toiletStationService.search(stationName);
            ResponseEntity<List<? extends IMapInfo>> entity = new ResponseEntity(iMapInfos,HttpStatus.OK);
            return entity;
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
