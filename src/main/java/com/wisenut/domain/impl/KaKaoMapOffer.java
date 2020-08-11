package com.wisenut.domain.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wisenut.domain.IMapInfo;
import com.wisenut.domain.IMapOffer;
import lombok.Getter;
import lombok.extern.java.Log;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Log
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class KaKaoMapOffer implements IMapOffer {

    @Value("${kakaoMapKey}")
    private String kakaoMapKey;
    @Value("${kakaoMapValue}")
    private String kakaoMapValue;

    private List<KaKaoMapInfo> documents;

    public KaKaoMapOffer(){
        super();
    }

    public KaKaoMapOffer (List<KaKaoMapInfo> documents){
        this.documents = documents;
    }

    /*
    웹 상에서 해당 정보를 크롤링하여 DB에 적재
     */
    @Override
    public void collectMapInfo() {
        try {
            String url = "https://namu.wiki/w/%EA%B0%9C%EC%B0%B0%EA%B5%AC%20%EC%95%88%EC%97%90%20%ED%99%94%EC%9E%A5%EC%8B%A4%EC%9D%B4%20%EC%9E%88%EB%8A%94%20%EC%97%AD";
            Connection connection = Jsoup.connect(url);
            connection.userAgent("Mozilla/5.0");
            Document doc = connection.get();

            Elements elements = doc.getElementsByClass("wiki-paragraph");
            List<String> tolietInStations = new ArrayList<>();

            for(Element element : elements){
                //System.out.println(element.attr("title"));
                Elements links = element.select("a[title]");
                for(Element title :  links){
                    String stationName = title.attr("title");
                    if(stationName.endsWith("역")){
                        tolietInStations.add(stationName);
                    }

                }
            }
            Collections.sort(tolietInStations);
            System.out.println(tolietInStations.stream().distinct().collect(Collectors.toList()));

            // TODO DB에 insert
            tolietInStations.stream().distinct().collect(Collectors.toList());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void getToiletInfoByStationNameFromAPI(String stationName) {
        ObjectMapper objectMapper = null;
        try{
            String encodedStationName = URLEncoder.encode(stationName,"UTF-8");
            URL url = new URL("http://dapi.kakao.com/v2/local/search/keyword.json?query="+encodedStationName+"&radius=10000&size=1");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", kakaoMapKey +" "+ kakaoMapValue);


            int responseCode = connection.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer sb = new StringBuffer();
            while((line = br.readLine())!=null){
                sb.append(line);
            }
            br.close();

            objectMapper = new ObjectMapper();
            KaKaoMapOffer kaKaoMapOffer= objectMapper.readValue(sb.toString(),KaKaoMapOffer.class);
            this.documents = kaKaoMapOffer.getDocuments();
            log.info(documents.toString());

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void getToiletInfoByOwnPositionFromAPI(String[] stationNames) {

    }


    @Override
    public List<? extends IMapInfo> searchStation(String stationName) {
        getToiletInfoByStationNameFromAPI(stationName);

        return documents;
    }
}
