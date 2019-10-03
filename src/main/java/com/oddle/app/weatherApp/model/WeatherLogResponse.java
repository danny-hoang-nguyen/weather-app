package com.oddle.app.weatherApp.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class WeatherLogResponse implements Serializable {

//    private String cityName;
//
//    private Long cityId;
//
//    private String wDate;
//
//    private String logDate;
//
//    private String wString;
//
//    private Double tempK;
//
//    private Double tempC;
//
//    private Double temmpF;
//
//    private String wMainType;
//
//    private String wIcon;
//
//    private Double pressure;
//
//    private Double humidity;
//
//    private Double windSpeed;

    private List<Weather> weather;
    private MainType main;
    private long id;
    private String name;
    private int cod;
    private Long dt;
    private Wind wind;
}
