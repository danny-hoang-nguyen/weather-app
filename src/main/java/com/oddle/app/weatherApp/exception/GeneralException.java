package com.oddle.app.weatherApp.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralException extends RuntimeException {
    private String message;

    public GeneralException(String message) {
        this.message = message;
    }
}