package com.oddle.app.weatherApp.controller;

import com.oddle.app.weatherApp.model.WeatherLog;
import com.oddle.app.weatherApp.service.WeatherLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
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

    @Test
    public void givenWrongCityName_whenFetchLogByCityName_thenReturnNotFound()
            throws Exception {

        String s = "{\n" +
                "    \"cod\": \"404\",\n" +
                "    \"message\": \"city not found\"\n" +
                "}";
        byte[] bytes = s.getBytes();

        HttpClientErrorException httpClientErrorException = new HttpClientErrorException(HttpStatus.NOT_FOUND, null, bytes, null);

        given(service.fetchLog("Hanoi123")).willThrow(httpClientErrorException);

        MvcResult mvcResult = mvc.perform(get("/fetch-log").param("cityName", "Hanoi123").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(404, mvcResult.getResponse().getStatus());
        String contentAsString = mvcResult.getResponse().getContentAsString();
        assertTrue(contentAsString.contains("city not found"));
        assertTrue(contentAsString.contains("Cannot get city: Hanoi123."));

    }

}
