package com.wisenut.web.apis;

import com.wisenut.domain.common.security.CurrentUser;
import com.wisenut.domain.model.IMapInfo;
import com.wisenut.domain.application.ToiletStationService;
import com.wisenut.domain.model.kakaomap.KaKaoMapInfo;
import com.wisenut.domain.model.user.SimpleUser;
import com.wisenut.web.payload.SearchPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ToiletSearchAPIController {

    private ToiletStationService toiletStationService;

    public ToiletSearchAPIController(ToiletStationService toiletStationService){
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

    @PostMapping("/api/calculation/distance")
    public ResponseEntity<String> calculateDistance(@RequestBody SearchPayload payload, @CurrentUser SimpleUser user){
        try{
            payload.setUserId(user.getUserId());
            String iMapInfo = toiletStationService.searchNearestStationName(payload.toCommand());
            return new ResponseEntity(iMapInfo,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
