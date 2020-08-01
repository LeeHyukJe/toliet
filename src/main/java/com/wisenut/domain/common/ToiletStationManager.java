package com.wisenut.domain.common;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ToiletStationManager {
    // html 파싱하여 역 목록 얻
    public List<String> readToiletStation(){
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

            //elements.stream().forEach(ele -> System.out.println(ele.toString()));

            return tolietInStations.stream().distinct().collect(Collectors.toList());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    // 각 역 이름 좌표 구하기

}
