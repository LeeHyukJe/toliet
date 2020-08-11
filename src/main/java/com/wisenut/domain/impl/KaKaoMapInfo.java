package com.wisenut.domain.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wisenut.domain.IMapInfo;
import com.wisenut.domain.dtos.KaKaoMapInfoDTO;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;
import java.net.URLEncoder;
import java.util.List;


@Component
@ToString
@Getter
@Log
public class KaKaoMapInfo implements IMapInfo {
    private String address_name;
    private String category_group_code;
    private String category_group_name;
    private String category_name;
    private String distance;
    private int id;
    private String phone;
    private String place_name;
    private String place_url;
    private String road_address_name;
    private String x;
    private String y;
}
