package com.oddle.app.weatherApp.service;

import com.oddle.app.weatherApp.exception.LogNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Service
public class RestService {

    @Value("${appId}")
    private String appId;

    @Value("${urlBase}")
    private String urlBase;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    private UriComponentsBuilder buildUri(Map<String, String> queryParam) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlBase);
        queryParam.forEach((s, s2) -> builder.queryParam(s, s2));

        builder.queryParam("appid", appId);
        return builder;

    }

    public String callRestToGetLog(Map<String, String> param) {
        String retrievedLog = "";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        URI url = buildUri(param).buildAndExpand().toUri();
//        try {
            retrievedLog = restTemplate().getForObject(url, String.class);
//        }
//        catch (HttpClientErrorException e) {
//            throw new LogNotFoundException(e.getResponseBodyAsString());
//        }

        return retrievedLog;
    }
}
