package com.oddle.app.weatherApp.controller;

import com.oddle.app.weatherApp.model.WeatherLog;
import com.oddle.app.weatherApp.service.AbstractTest;
import com.oddle.app.weatherApp.service.WeatherLogService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class WeatherControllerTest extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @MockBean
    WeatherLogService service;

    @Test
    public void givenCityName_whenFetchLogByCityName_thenReturn() throws Exception {

        WeatherLog gothamCity = new WeatherLog();
        gothamCity.setCityName("Gotham");
        given(service.fetchLog(Mockito.anyString())).willReturn(gothamCity);
        String uri = "/fetch-log";
        mvc.perform(get(uri).param("cityName", "Gotham")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andExpect(jsonPath("$.cityName").value("Gotham"));
    }

//    @Test
//    public void givenWrongCityName_whenFetchLogByCityName_thenReturnNotFound() throws Exception {
//
//        given(service.fetchLog(Mockito.anyString())).willThrow(new IntegrationException("Cannot get call api to get log of city: Gotham123"));
//        String uri = "/fetch-log";
//        MvcResult mvcResult = mvc.perform(get(uri).param("cityName", "Gotham123")
//                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//        mvcResult.getResolvedException().getMessage().contains("Cannot get call api to get log of city: Gotham123");
//        assertTrue(mvcResult.getResponse().getStatus() == 500);
//
//    }

    @Test
    public void givenNoParam_whenRetrievingLogs_thenReturn() throws Exception {

        given(service.retrieveLogs(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())).willReturn(Collections.emptyList());
        String uri = "/weather-logs";
        mvc.perform(get(uri)

                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
}
