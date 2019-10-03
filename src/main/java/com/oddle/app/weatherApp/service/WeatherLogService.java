package com.oddle.app.weatherApp.service;

import com.oddle.app.weatherApp.model.WeatherLog;

import java.util.List;

public interface WeatherLogService {

    List<WeatherLog> retrieveLogs(String cityName, String date, Integer pageOrder, Integer count);

    WeatherLog fetchLog(String cityName);

    WeatherLog updateSavedLog(Long id, WeatherLog weatherLog);

    void deleteSavedLog(Long id);
}
