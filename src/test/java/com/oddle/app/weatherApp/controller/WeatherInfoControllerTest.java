package com.oddle.app.weatherApp.controller;

import com.oddle.app.weatherApp.exception.IntegrationException;
import com.oddle.app.weatherApp.model.WeatherLog;
import com.oddle.app.weatherApp.service.RestService;
import com.oddle.app.weatherApp.service.WeatherLogService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WeatherInfoController.class)
public class WeatherInfoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WeatherLogService service;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private RestService restService;

    @Test
    public void givenLogOfHaNoi_whenFetchLogByCityName_thenReturnJson()
            throws Exception {

        WeatherLog weatherLog = new WeatherLog();
        weatherLog.setCityName("Hanoi");


        given(service.fetchLog("Hanoi")).willReturn(weatherLog);

        mvc.perform(get("/fetch-log").param("cityName", "Hanoi")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cityName", is(weatherLog.getCityName())));
    }

}
