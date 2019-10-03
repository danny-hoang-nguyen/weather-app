package com.oddle.app.weatherApp.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Weather implements Serializable {

    private long id;
    private String main;
    private String description;
    private String icon;
}
