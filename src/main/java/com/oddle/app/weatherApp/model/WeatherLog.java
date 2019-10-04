package com.oddle.app.weatherApp.model;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class WeatherLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String cityName;
    private Long cityId;
    private Long wDate;
    private String logDate;
    private String wStr;
    private Double tempK;
    private Double tempC;
    private Double tempF;
    private String wMainType;
    private String wIcon;
    private Double pressure;
    private Double humidity;
    private Double windSpeed;


}
