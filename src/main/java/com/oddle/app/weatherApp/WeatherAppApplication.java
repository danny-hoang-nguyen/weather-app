package com.oddle.app.weatherApp;

import com.google.gson.Gson;
import com.oddle.app.weatherApp.model.City;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@ComponentScan(basePackages = "com.oddle.app")
public class WeatherAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherAppApplication.class, args);
        try {
            readAndExtract();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void readAndExtract() throws IOException {
        Set<String> arrayList = new HashSet<>();
              File file = ResourceUtils.getFile("classpath:city.list.json");
//
            String content = new String(Files.readAllBytes(file.toPath()));
            Gson gson = new Gson();
            City[] cities = gson.fromJson(content, City[].class); //input is your String
            for (City c : cities) {
                arrayList.add(c.getName());
            }
        BufferedWriter writer = new BufferedWriter(new FileWriter("extracted.json"));
        writer.write(gson.toJson(arrayList));

        writer.close();
    }
}
