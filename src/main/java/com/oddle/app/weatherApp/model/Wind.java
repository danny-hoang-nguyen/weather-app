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
public class Wind implements Serializable {

    private static final long serialVersionUID = 1L;

    private Double speed;
    private Double degree;
}
