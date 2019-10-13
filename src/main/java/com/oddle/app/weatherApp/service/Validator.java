package com.oddle.app.weatherApp.service;

public interface Validator {

    default boolean isValid(String dateString) {
        return false;
    }
}
