package com.oddle.app.weatherApp.service.impl;

import com.oddle.app.weatherApp.service.DateValidator;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class DateValidatorImpl implements DateValidator {

    private static final String dateFormat = "yyyy-MM-dd";


    @Override
    public boolean isValid(String dateString) {
        DateFormat sdf = new SimpleDateFormat(this.dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(dateString);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
