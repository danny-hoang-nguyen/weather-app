package com.oddle.app.weatherApp.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MainType implements Serializable {
    private Double temp;
    private Double pressure;
    private Double humidity;
}
