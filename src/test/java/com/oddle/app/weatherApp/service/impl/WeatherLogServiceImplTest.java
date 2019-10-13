package com.oddle.app.weatherApp.service.impl;

import com.oddle.app.weatherApp.exception.IntegrationException;
import com.oddle.app.weatherApp.service.RestService;
import com.oddle.app.weatherApp.service.WeatherLogService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class WeatherLogServiceImplTest {

    @InjectMocks
    WeatherLogService service = new WeatherLogServiceImpl();

    @Mock
    RestService restService;

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
