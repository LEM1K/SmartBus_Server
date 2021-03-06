package com.example.demo.utils;


public enum APIHandler {
    //버스 노선 정보
    ROUTE_CITY_LIST,                            //(도착 정보조회 서비스)노선정보 지원 도시 목록
    ROUTE_NUMBER_LIST,                          //(버스 노선 정보조회 서비스)노선 번호 목록 조회
    ROUTE_THROUGH_STATION_LIST,                 //(버스 노선 정보조회 서비스)노선별 경유 정류소 목록 조회
    ROUTE_INFO,                                 //(버스 노선 정보조회 서비스)노선 기본 정보 조회

    //버스 도착 정보
    ARRIVE_CITY_LIST,                           //도착정보 지원 도시 목록
    ARRIVE_SPECIFY_STATION_ACCESS_BUS_LIST,     //(도착 정보조회 서비스)정류소별 특정노선 도착예정 정보 및 운행 정보 조회
    ARRIVE_BUS_LIST,                            //(도착 정보조회 서비스)정류소별 도착예정 정보 목록 조회

    //버스 정류장 정보
    STATION_CITY_LIST,                          //정류장 정보 지원 도시 목록
    STATION_NUMBER_LIST,                        //(버스 정류소 정보 조회 서비스)정류소 번호 목록 조회
    STATION_SPECIFY_LOCATION_LIST,              //(버스 정류소 정보 조회 서비스)좌표 기반 정류소 목록 조회

    //버스 위치 정보
    LOCATION_CITY_LIST,                         //위치정보 지원 도시 목록
    LOCATION_BUS_LIST,                          //(버스 위치 정보조회 서비스)노선별 위치 정보 조회
    LOCATION_SPECIFY_STATION_ACCESS_BUS_LIST,   //(버스 위치 정보조회 서비스)특정 정류소에 근접한 버스 정보 조회

    GET_COORDINATE,                             //도착지의 위도 경도 조회

    //경로 연산
    FIND_WAY,                                   //출발지와 도착지 간의 경로 조회
    TEST_FIND_WAY,

    //지역 기상 정보
    REGION_DUST_INFO,
    REGION_WEATHER_INFO,



}