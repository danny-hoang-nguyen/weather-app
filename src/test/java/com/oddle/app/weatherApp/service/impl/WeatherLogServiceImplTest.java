package com.oddle.app.weatherApp.service.impl;

import com.oddle.app.weatherApp.dao.LogRepository;
import com.oddle.app.weatherApp.exception.IntegrationException;
import com.oddle.app.weatherApp.service.DateValidator;
import com.oddle.app.weatherApp.service.RestService;
import com.oddle.app.weatherApp.service.WeatherLogService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class WeatherLogServiceImplTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public WeatherLogService logService() {
            return new WeatherLogServiceImpl();
        }
    }

    @Autowired
    WeatherLogService service;

    @MockBean
    RestService restService;

    @MockBean
    RestTemplate restTemplate;

    @MockBean
    LogRepository logRepository;

    @MockBean
    DateValidator dateValidator;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void givenWrongCityName_whenFetchLogByCityName_thenReturnNotFound() {
        String s = "{" +
                "    \"cod\": \"404\"," +
                "    \"message\": \"city not found\"" +
                "}";
        given(restService.callRestToGetLog(Mockito.anyMap())).willThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND, null, s.getBytes(), null));
        exceptionRule.expect(IntegrationException.class);
        exceptionRule.expectMessage("Cannot get call api to get log of city: Gotham123 .Detail: " + s);
        service.fetchLog("Gotham123");
    }
}
