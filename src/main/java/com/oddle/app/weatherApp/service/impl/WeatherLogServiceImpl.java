package com.oddle.app.weatherApp.service.impl;

import com.google.gson.Gson;
import com.oddle.app.weatherApp.dao.LogRepository;
import com.oddle.app.weatherApp.entity.WeatherLogEntity;
import com.oddle.app.weatherApp.exception.GeneralException;
import com.oddle.app.weatherApp.exception.LogNotFoundException;
import com.oddle.app.weatherApp.model.WeatherLog;
import com.oddle.app.weatherApp.model.WeatherLogResponse;
import com.oddle.app.weatherApp.service.DateValidator;
import com.oddle.app.weatherApp.service.RestService;
import com.oddle.app.weatherApp.service.WeatherLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WeatherLogServiceImpl implements WeatherLogService {

    public static final int DEFAULT_PAGE = 0;
    public static final int DEFAULT_PAGE_SIZE = 100;
    public static final int ONE_DAY_IN_SEC = 86400;

    @Value("${appId}")
    private String appId;


    public WeatherLog fromEntity(WeatherLogEntity wLogEntity) {
        return WeatherLog.builder()
                .cityId(wLogEntity.getCityId())
                .id(wLogEntity.getId())
                .cityName(wLogEntity.getCityName())
                .wDate(wLogEntity.getWDate())
                .logDate(wLogEntity.getLogDate())
                .wStr(wLogEntity.getWString())
                .tempC((wLogEntity.getTempC()))
                .tempF(wLogEntity.getTempF())
                .tempK(wLogEntity.getTempK())
                .wMainType(wLogEntity.getWMainType())
                .wIcon(wLogEntity.getWIcon())
                .pressure(wLogEntity.getPressure())
                .humidity(wLogEntity.getHumidity())
                .windSpeed(wLogEntity.getWindSpeed())
                .build();
    }

    public WeatherLogEntity fromModel(WeatherLog wLog) {
        return WeatherLogEntity.builder()
                .cityId(wLog.getCityId())
                .cityName(wLog.getCityName())
                .wDate(wLog.getWDate())
                .logDate(wLog.getLogDate())
                .wString(wLog.getWStr())
                .tempC(wLog.getTempC())
                .tempK(wLog.getTempK())
                .tempF(wLog.getTempF())
                .wMainType(wLog.getWMainType())
                .wIcon(wLog.getWIcon())
                .pressure(wLog.getPressure())
                .humidity(wLog.getHumidity())
                .windSpeed(wLog.getWindSpeed())
                .build();
    }

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private DateValidator dateValidator;

    @Autowired
    private RestService restService;

    public void deleteSavedLog(Long id) {
        if (getLogById(id) != null) {
            logRepository.deleteById(id);
        } else throw new LogNotFoundException("id: " + id + " does not exist");
    }

    public WeatherLog updateSavedLog(Long id, WeatherLog weatherLog) {
        Instant instant = Instant.now();

        long timeStampSeconds = instant.getEpochSecond();
        WeatherLog weatherLogById = getLogById(id);
        if (weatherLogById != null) {
            if (timeStampSeconds - weatherLogById.getWDate() >= ONE_DAY_IN_SEC) {
                throw new GeneralException("Cannot edit log older than 1 day from: " + weatherLogById.getLogDate() + " to current time: " + convertMiliToDateTime(timeStampSeconds));
            }
            WeatherLogEntity weatherLogEntity = fromModel(weatherLog);
            weatherLogEntity.setId(id);
            logRepository.save(weatherLogEntity);
            return fromEntity(logRepository.findById(id).get());
        }
        return null;
    }

    public WeatherLog getLogById(Long id) {
        Optional<WeatherLogEntity> weatherLogEntity = logRepository.findById(id);
        if (weatherLogEntity.isPresent()) {
            return fromEntity(weatherLogEntity.get());
        }
        return null;
    }

    public List<WeatherLog> retrieveLogs(String cityName, String date, Integer pageOrder, Integer count) {
        Pageable pageable;
        List<WeatherLog> result = Collections.emptyList();
        if (pageOrder != null && count != null) {
            pageable = PageRequest.of(pageOrder, count, Sort.by("wDate").descending());
        } else {
            pageable = PageRequest.of(DEFAULT_PAGE, DEFAULT_PAGE_SIZE, Sort.by("wDate").descending());
        }

        if (cityName == null && date == null) {
            result = logRepository.findAll(pageable).stream()
                    .map(weatherLogEntity -> fromEntity(weatherLogEntity)).collect(Collectors.toList());
        } else if (cityName != null && date != null) {
            if (!dateValidator.isValid(date))
                throw new GeneralException("Date is invalid format. Please use yyyy-mm-dd format.");
            result = logRepository.findAllByCityNameAndLogDate(cityName, date, pageable).stream()
                    .map(weatherLogEntity -> fromEntity(weatherLogEntity)).collect(Collectors.toList());
        }


        return result;
    }

    public WeatherLog fetchLog(String name) {

        Map<String, String> param = new HashMap<>();
        param.put("q", name);
        String response = restService.callRestToGetLog(param);
        WeatherLogEntity weatherLogEntity = saveLatestLog(response);
        return fromEntity(weatherLogEntity);

    }



    public WeatherLogEntity saveLatestLog(String input) {
        Gson gson = new Gson();
        WeatherLogEntity wEntity = new WeatherLogEntity();
        WeatherLogResponse weatherLogResponse = gson.fromJson(input, WeatherLogResponse.class);
        weatherLogResponse.getWeather().stream().forEach(e -> {

            wEntity.setCityId(weatherLogResponse.getId());
            wEntity.setCityName(weatherLogResponse.getName());
            wEntity.setLogDate(convertMiliToDateTime(weatherLogResponse.getDt()));
            wEntity.setWDate(weatherLogResponse.getDt());
            wEntity.setWindSpeed(weatherLogResponse.getWind().getSpeed());
            wEntity.setHumidity(weatherLogResponse.getMain().getHumidity());
            wEntity.setPressure(weatherLogResponse.getMain().getPressure());
            wEntity.setTempF(convertKtoF(weatherLogResponse.getMain().getTemp()));
            wEntity.setTempK(weatherLogResponse.getMain().getTemp());
            wEntity.setTempC(convertKtoC(weatherLogResponse.getMain().getTemp()));
            wEntity.setWMainType(e.getMain());
            wEntity.setWIcon(e.getIcon());
            wEntity.setWString(input);
            logRepository.save(wEntity);
        });
        return wEntity;

    }

    private String convertMiliToDateTime(long mili) {
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd");

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(mili);

        String formatted = df.format(new Date(mili * 1000));
        return formatted;

    }

    private Double convertKtoC(Double degree) {
        double converted = degree - 273.15;
        return Math.floor(converted * 100 / 100);
    }

    private Double convertKtoF(Double degree) {
        double converted = degree * 9 / 5 - 459.67;
        return Math.floor(converted * 100 / 100);
    }

}

