package com.oddle.app.weatherApp.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class WeatherLogResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Weather> weather;
    private MainType main;
    private long id;
    private String name;
    private int cod;
    private Long dt;
    private Wind wind;
}
