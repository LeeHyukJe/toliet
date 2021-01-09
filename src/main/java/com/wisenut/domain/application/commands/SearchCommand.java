package com.wisenut.domain.application.commands;

import lombok.*;

@Getter
@RequiredArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class SearchCommand {

    private final String stationName;
    private final String addressName;
    private final String placeName;
    private final String x;
    private final String y;
    private final long userId;

//    public SearchCommand(String stationName,String addressName, String placeName, String x, String y){
//        this.stationName = stationName;
//        this.addressName = addressName;
//        this.x = x;
//        this.y = y;
//    }


}
