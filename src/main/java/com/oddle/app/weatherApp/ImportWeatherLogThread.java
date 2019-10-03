package com.oddle.app.weatherApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;

public class ImportWeatherLogThread implements Runnable{

    private static final String urlBase ="http://api.openweathermap.org/data/2.5/group";
    private String name;
    private RestTemplate restTemplate;
    public ImportWeatherLogThread(String name, RestTemplate restTemplate) {
        this.restTemplate=restTemplate;
        this.name=name;
    }

//    public RestTemplate getRestTemplate() {
//        if (this.restTemplate==null) return new RestTemplate();
//        return this.restTemplate;
//    }
    @Override
    public void run() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlBase)
                .queryParam("id", name)
                .queryParam("units", "metric")
                .queryParam("appid","eb9d3715a73a84a225a09c557ea368dc");


        HttpEntity<String> entity = new HttpEntity<String>(headers);

//        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
        String result = response.getBody();
        //TODO use dao to save these response into database
    }
}
