package com.oddle.app.weatherApp.model;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class City implements Serializable {

    private Long id;
    private String name;
    private String country;
    private Coordination coord;

}
