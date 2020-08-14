package com.wisenut.web.apis;

import com.wisenut.domain.model.IMapInfo;
import com.wisenut.domain.application.ToiletStationService;
import com.wisenut.web.payload.SearchPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ToiletStationController {

    private ToiletStationService toiletStationService;

    public ToiletStationController(ToiletStationService toiletStationService){
        this.toiletStationService = toiletStationService;
    }

    @GetMapping("/api/search")
    public ResponseEntity<List<? extends IMapInfo>> searchToiletStation(@ModelAttribute SearchPayload payload){
        try{
            List<? extends IMapInfo> iMapInfos = toiletStationService.search(payload.toCommand());
            ResponseEntity<List<? extends IMapInfo>> entity = new ResponseEntity(iMapInfos,HttpStatus.OK);
            return entity;
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/calculation/distance")
    public ResponseEntity<List<? extends IMapInfo>> calculateDistance(@RequestBody Map<String, Object> params){
        try{

            IMapInfo iMapInfo = toiletStationService.calculateDistance(null);
            return new ResponseEntity(iMapInfo,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
