package com.oddle.app.weatherApp.model;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Wind implements Serializable {
    private Double speed;
    private Double degree;
}
