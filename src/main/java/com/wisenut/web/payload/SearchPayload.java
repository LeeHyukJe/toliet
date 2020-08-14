package com.wisenut.web.payload;

import com.wisenut.domain.application.commands.SearchCommand;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class SearchPayload {

    @Size(min = 3, max = 10, message = "역 이름은 빈 값일 수 없습니다.")
    @NotNull
    private String stationName;

    public SearchCommand toCommand(){
        return new SearchCommand(this.stationName);
    }
}
