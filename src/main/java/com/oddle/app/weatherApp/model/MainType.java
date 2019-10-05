package com.oddle.app.weatherApp.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MainType implements Serializable {

    private static final long serialVersionUID = 1L;

    private Double temp;
    private Double pressure;
    private Double humidity;
}
