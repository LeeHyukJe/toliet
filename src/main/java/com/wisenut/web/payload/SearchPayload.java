package com.wisenut.web.payload;

import com.wisenut.domain.application.commands.SearchCommand;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class SearchPayload {
    // 누가 검색하는지 파악
    // private String userId;

    @Size(min = 3, max = 10, message = "역 이름은 빈 값일 수 없습니다.")
    @NotNull
    private String stationName;

    @Size(min = 2, message = "주소 이름은 null 값일 수 없습니다.")
    @NotNull
    private String address_name;

    @Size(min = 2, message = "위치 이름은 null 값일 수 없습니다.")
    @NotNull
    private String place_name;

    @Size(min=2, message = "좌표값은 빈 값일 수 없습니다.")
    @NotNull
    private String x;

    @Size(min = 2, message = "죄표값은 빈 값일 수 없습니다.")
    @NotNull
    private String y;

    public SearchCommand toCommand(){
        return new SearchCommand(this.stationName,this.address_name,this.place_name,this.x, this.y);
    }
}
