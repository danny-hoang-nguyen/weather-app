package com.oddle.app.weatherApp.service;

import com.google.gson.Gson;
import com.oddle.app.weatherApp.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.ws.ServiceMode;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

@Service
public class DataFetcherService {
//    private static final String urlBase ="http://api.openweathermap.org/data/2.5/group";

    private final RestTemplate restTemplate;



    public DataFetcherService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async
    public CompletableFuture<String> readCity(String temp) {
        File file = null; String result="";
        try {
            file = ResourceUtils.getFile("classpath:city.list.json");
            String content = new String(Files.readAllBytes(file.toPath()));
            Gson gson = new Gson();
            City[] cities = gson.fromJson(content, City[].class);
//            for (City c : cities) {
//                if (i < 20) {
//                    temp = temp + c.getId() +",";
//                    i++;
//                } else {
                    //TODO replace by thread to make sure the app can start while the initialization keeps going
//                    temp=temp.replaceFirst(".$","");
                    HttpHeaders headers = new HttpHeaders();
                    headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://api.openweathermap.org/data/2.5/group")
                            .queryParam("id", temp)
                            .queryParam("units", "metric")
                            .queryParam("appid","eb9d3715a73a84a225a09c557ea368dc");


                    HttpEntity<String> entity = new HttpEntity<String>(headers);

                    RestTemplate restTemplate = new RestTemplate();

                    ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
                    result = response.getBody();
//                    i=0; temp="";
//                }
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return CompletableFuture.completedFuture(result);
    }
}
