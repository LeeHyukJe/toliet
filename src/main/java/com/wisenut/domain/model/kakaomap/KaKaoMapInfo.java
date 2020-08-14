package com.wisenut.domain.model.kakaomap;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wisenut.domain.model.IMapInfo;
import lombok.*;
import lombok.extern.java.Log;

import org.springframework.stereotype.Component;

import javax.persistence.*;


@Component
@ToString
@Getter
@Log
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KaKaoMapInfo implements IMapInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty(value = "address_name")
    private String addressName;
    @JsonProperty(value = "category_group_code")
    private String categoryGroupCode;
    @JsonProperty(value = "category_group_name")
    private String categoryGroupName;
    @JsonProperty(value = "category_name")
    private String categoryName;
    private String distance;
    private String phone;
    @JsonProperty(value = "place_name")
    private String placeName;
    @JsonProperty(value = "place_url")
    private String placeUrl;
    @JsonProperty(value = "road_address_name")
    private String roadAddressName;
    private String x;
    private String y;

}
