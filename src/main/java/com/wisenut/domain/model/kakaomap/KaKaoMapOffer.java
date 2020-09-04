package com.wisenut.domain.model.kakaomap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wisenut.domain.model.IMapInfo;
import com.wisenut.domain.model.IMapOffer;
import lombok.*;
import lombok.extern.java.Log;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.*;
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
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Map")
public class KaKaoMapOffer implements IMapOffer {

    @Transient
    @Value("${kakaoMapKey}")
    private String kakaoMapKey;

    @Transient
    @Value("${kakaoMapValue}")
    private String kakaoMapValue;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "mapid")
    private List<KaKaoMapInfo> documents;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "mapid")
    private List<KakaoMapSearch> searchList;



//    public KaKaoMapOffer (List<KaKaoMapInfo> documents){
//        this.documents = documents;
//    }

    /**
     * update를 위한 수정자 메소드
     * @return
     */
    public void update(List<KaKaoMapInfo> documents, List<KakaoMapSearch> searchList){
        this.documents = documents;
        this.searchList = searchList;
    }

    /*
    웹 상에서 해당 정보를 크롤링하여 DB에 적재
     */
    @Override
    public List<String> collectMapInfo() {
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
//            System.out.println(tolietInStations.stream().distinct().collect(Collectors.toList()));


            List<String> toiletInStation = tolietInStations.stream().distinct().collect(Collectors.toList());
            return toiletInStation;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
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

    public List<Integer> distance(List<? extends IMapInfo> infos){

        return null;
    }

    @Override
    public String calculateDistance(List<? extends IMapInfo> iMapInfos, String x, String y) {
        List<KaKaoMapInfo> kakaoMapInfoList = new ArrayList(iMapInfos);
        List<Double> temp = new ArrayList<>();
        for(IMapInfo info : iMapInfos){
            KaKaoMapInfo kaKaoMapInfo = (KaKaoMapInfo)info;
            double compareX = Double.parseDouble(kaKaoMapInfo.getX()); // 경도 (longitude)
            double compareY = Double.parseDouble(kaKaoMapInfo.getY()); // 위도 (latitude)

            double compare2X = Double.parseDouble(x);
            double compare2Y = Double.parseDouble(y);

            double result = Math.sqrt((compareX-compare2X)*(compareX-compare2X) + (compareY - compare2Y) * (compareY - compare2Y));

            double theta = compareX - compare2X;
            double dist = Math.sin(deg2rad(compareY)) * Math.sin(deg2rad(compare2Y)) + Math.cos(deg2rad(compareY)) * Math.cos(deg2rad(compare2Y)) * Math.cos(deg2rad(theta));

            String unit = "kilometer";

            dist = Math.acos(dist);
            dist = rad2deg(dist);
            dist = dist * 60 * 1.1515;

            if (unit == "kilometer") {
                dist = dist * 1.609344;
            } else if(unit == "meter"){
                dist = dist * 1609.344;
            }

            temp.add(dist);
        }

        // 배열 리스트 중에 가장 최솟값을 구하기
        Double min = temp.get(0);
        int index=0;
        for(int i=0 ; i<temp.size();i++){
            if(Double.compare(min,temp.get(i)) > 0){
                min = temp.get(i);
                index = i;
            }
        }


        return kakaoMapInfoList.get(index).getPlaceName();
    }

    // This function converts decimal degrees to radians
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // This function converts radians to decimal degrees
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
