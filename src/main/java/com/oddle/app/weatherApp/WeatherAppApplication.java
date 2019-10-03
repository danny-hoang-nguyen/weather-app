package com.oddle.app.weatherApp;

import com.oddle.app.weatherApp.service.DataFetcherService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.oddle.app")
public class WeatherAppApplication {


    private DataFetcherService dataFetcherService;

    public WeatherAppApplication(DataFetcherService dataFetcherService) {
        this.dataFetcherService = dataFetcherService;
    }

    private static final String urlBase = "http://api.openweathermap.org/data/2.5/group";

    public static void main(String[] args) {


        SpringApplication.run(WeatherAppApplication.class, args);
//		JSONArray jsonarray = new JSONArray(jsonStr);
//		for (int i = 0; i < jsonarray.length(); i++) {
//			JSONObject jsonobject = jsonarray.getJSONObject(i);
//			String name = jsonobject.getString("name");
//			String url = jsonobject.getString("url");
//		}


//        File file = null;
//        int i = 0;
//        String temp = "";
//        try {
//            file = ResourceUtils.getFile("classpath:city.list.json");
//
//            String content = new String(Files.readAllBytes(file.toPath()));
//            Gson gson = new Gson();
//            City[] cities = gson.fromJson(content, City[].class); //input is your String
//            for (City c : cities) {
//                if (i < 20) {
//                    temp = temp + c.getId() +",";
//
//                    i++;
//                } else {
//                    temp=temp.replaceFirst(".$","");
//                    ImportWeatherLogThread thread = new ImportWeatherLogThread(temp, getRestTemplate());
//                    Thread t1= new Thread(thread);
//                    t1.start();

//                    HttpHeaders headers = new HttpHeaders();
//                    headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
//                    headers.setContentType(MediaType.APPLICATION_JSON);
////                    https://samples.openweathermap.org/data/2.5/weather?id=2172797&appid=b6907d289e10d714a6e88b30761fae22
//                    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlBase)
//                            .queryParam("id", temp)
//                            .queryParam("units", "metric")
//                            .queryParam("appid","eb9d3715a73a84a225a09c557ea368dc");
//
////                    headers.set("my_other_key", "my_other_value");
//
//                    HttpEntity<String> entity = new HttpEntity<String>(headers);
//
//                    RestTemplate restTemplate = new RestTemplate();
//
//                    ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
//                    String result = response.getBody();
//                    System.out.println(result);
//                    i=0; temp="";
//                }
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println("end game");
    }


}
