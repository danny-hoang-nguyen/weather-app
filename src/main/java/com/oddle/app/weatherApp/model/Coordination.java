package com.oddle.app.weatherApp.model;


import com.google.gson.annotations.SerializedName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Coordination implements Serializable {

    private static final long serialVersionUID = 1L;

    @SerializedName("lat")
    private Double lattitude;

    @SerializedName("lon")
    private Double longitude;

}
