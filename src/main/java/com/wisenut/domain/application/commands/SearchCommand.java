package com.wisenut.domain.application.commands;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class SearchCommand {

    private String stationName;

    public SearchCommand(String stationName){
        this.stationName = stationName;
    }


}
