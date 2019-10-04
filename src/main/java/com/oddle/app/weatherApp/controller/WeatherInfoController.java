package com.oddle.app.weatherApp.controller;

import com.oddle.app.weatherApp.exception.LogNotFoundException;
import com.oddle.app.weatherApp.model.WeatherLog;
import com.oddle.app.weatherApp.service.WeatherLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class WeatherInfoController {

    @Autowired
    private WeatherLogService service;

    @RequestMapping(value = {"/fetch-log"}, method = RequestMethod.GET)
    public ResponseEntity<?> fetchLatestLogByCity(
            @RequestParam(value = "cityName") String cityName) {
        WeatherLog log = null;
        try {
            log = service.fetchLog(cityName);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(new LogNotFoundException("Cannot get city: " + cityName + ". Detail: " + e.getResponseBodyAsString()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(log, HttpStatus.OK);
    }

    @RequestMapping(value = {"/weather-logs"}, method = RequestMethod.GET)
    public ResponseEntity<?> getAllWeatherLogs(
            @RequestParam(value = "date", required = false) String date,
            @RequestParam(value = "cityName", required = false) String cityName,
            @RequestParam(value = "pageOrder", required = false) Integer pageOrder,
            @RequestParam(value = "itemCount", required = false) Integer count
    ) {
        return new ResponseEntity<>(service.retrieveLogs(cityName, date, pageOrder, count), HttpStatus.OK);
    }

    @RequestMapping(value = {"/weather-logs/{id}"}, method = RequestMethod.PUT)
    public ResponseEntity<?> updateWeatherLog(@PathVariable("id") Long id, @RequestBody WeatherLog weatherLog) {
        WeatherLog log = service.updateSavedLog(id, weatherLog);
        if (log == null) {
            throw new LogNotFoundException("id: " + id + " does not exist");
        }
        return ResponseEntity.ok(log);
    }

    @RequestMapping(value = {"/weather-logs/{id}"}, method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteWeatherLogs(@PathVariable("id") Long id) {
        service.deleteSavedLog(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
