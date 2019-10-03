package com.oddle.app.weatherApp.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cityName;

    private long cityId;

    private Long wDate;

    private String logDate;

    @Column(length = 5000)
    private String wString;

    private Double tempK;

    private Double tempC;

    private Double tempF;

    private String wMainType;

    private String wIcon;

    private Double pressure;

    private Double humidity;

    private Double windSpeed;

}
