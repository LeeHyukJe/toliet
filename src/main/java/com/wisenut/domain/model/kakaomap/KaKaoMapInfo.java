package com.wisenut.domain.model.kakaomap;

import com.wisenut.domain.model.IMapInfo;
import lombok.*;
import lombok.extern.java.Log;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


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
    @GeneratedValue
    private Long id;

    private String address_name;
    private String category_group_code;
    private String category_group_name;
    private String category_name;
    private String distance;
    private String phone;
    private String place_name;
    private String place_url;
    private String road_address_name;
    private String x;
    private String y;
}
