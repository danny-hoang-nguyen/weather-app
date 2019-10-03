package com.oddle.app.weatherApp.model;


import com.google.gson.annotations.SerializedName;
import lombok.ToString;

import java.io.Serializable;

@ToString
public class Coordination implements Serializable {
//TODO add uid
    @SerializedName("lat")
    private Double lattitude;

    @SerializedName("lon")
    private Double longitude;

}
