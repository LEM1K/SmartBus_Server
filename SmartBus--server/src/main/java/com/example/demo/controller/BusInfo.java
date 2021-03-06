package com.example.demo.controller;

import com.example.demo.datacenter.DataCenter;
import com.example.demo.utils.APIHandler;
import com.example.demo.utils.TrafficAPIReceiver;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

//버스 노선에 대한 정보만을 받아오는 API

/* 기능
 * 1. 매개변수로 입력받은 버스노선에 대한 상세 정보
 * 2. 매개변수로 입력받은 버스노선에 대해 경유하는 모든 정류장을 출력. (List)
 * 3. 매개변수로 입력받은 버스노선 별로 현재 운행중인 버스의 gps 정보 출력 (List)
 */


@RestController
public class BusInfo {

    public static String cityCode;
    public static String routeId;
    public static String routeNo;


    //매개변수로 입력받은 버스노선에 대한 상세 정보
    //노선 번호 -> 노선 id로 파싱된 Arraylists는 DataCenter에 저장
    @RequestMapping(method = RequestMethod.GET, path = "/getBusList")
    JSONArray getBusList(@RequestParam String cityName, @RequestParam String routeNo) throws InterruptedException {

        PublicOperation pub = new PublicOperation();

        DataCenter.Singleton().routeIdList.clear();
        DataCenter.Singleton().finalRouteList.clear();

        this.cityCode = pub.getCityCode(cityName, APIHandler.ROUTE_CITY_LIST);
        DataCenter.Singleton().routeIdList = pub.getRouteId(cityName, routeNo);

        for(Object obj : DataCenter.Singleton().routeIdList) {
            this.routeId = (String) obj;
            String routeId = this.routeId;
            TrafficAPIReceiver trafficAPIReceiver = new TrafficAPIReceiver(APIHandler.ROUTE_INFO);
            trafficAPIReceiver.start();

            while(!trafficAPIReceiver.isDone){
                Thread.sleep(100);
            }

            //Thread.sleep(1000);

            JSONArray routeList = DataCenter.Singleton().routeInfoList;
            JSONArray subResult = new JSONArray();

            for(Object o : routeList) {
                JSONObject json = (JSONObject) o;
                if(json.get("routeId").toString().contains(routeId)) {
                    subResult.add(json);
                }
            }
            DataCenter.Singleton().finalRouteList.add(subResult);
        }

        return DataCenter.Singleton().finalRouteList;
    }

    //매개변수로 입력받은 버스노선에 대해 경유하는 모든 정류장을 출력
    @RequestMapping(method = RequestMethod.GET, path = "/getThroughStation")
    JSONArray getThroughStationList(@RequestParam String cityName, @RequestParam String routeNo) throws InterruptedException {

        PublicOperation pub = new PublicOperation();
        DataCenter.Singleton().routeIdList.clear();
        DataCenter.Singleton().finalRouteAceessList.clear();

        this.cityCode = pub.getCityCode(cityName, APIHandler.ROUTE_CITY_LIST);
        DataCenter.Singleton().routeIdList = pub.getRouteId(cityName, routeNo);

        for(Object obj : DataCenter.Singleton().routeIdList) {
            this.routeId = (String) obj;
            this.routeNo = routeNo;
            String routeId = this.routeId;
            TrafficAPIReceiver trafficAPIReceiver = new TrafficAPIReceiver(APIHandler.ROUTE_THROUGH_STATION_LIST);
            trafficAPIReceiver.start();

            while(!trafficAPIReceiver.isDone) {
                Thread.sleep(100);
            }

            //Thread.sleep(1000);

            JSONArray accessStationList = DataCenter.Singleton().accessStationList;
            JSONArray subResult = new JSONArray();

            for(Object o : accessStationList) {
                JSONObject json = (JSONObject) o;
                if(json.get("routeId").toString().contains(routeId)) {
                    subResult.add(json);
                }
            }
            DataCenter.Singleton().finalRouteAceessList.add(subResult);
        }

        return DataCenter.Singleton().finalRouteAceessList;


    }


 }
