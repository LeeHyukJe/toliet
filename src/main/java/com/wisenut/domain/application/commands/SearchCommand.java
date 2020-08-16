package com.wisenut.domain.application.commands;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class SearchCommand {

    private String stationName;
    private String addressName;
    private String placeName;
    private String x;
    private String y;

    public SearchCommand(String stationName,String addressName, String placeName, String x, String y){
        this.stationName = stationName;
        this.addressName = addressName;
        this.x = x;
        this.y = y;
    }


}
