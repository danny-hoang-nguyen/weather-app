package com.oddle.app.weatherApp.model;


import com.oddle.app.weatherApp.entity.WeatherLogEntity;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class WeatherLog implements Serializable {
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
