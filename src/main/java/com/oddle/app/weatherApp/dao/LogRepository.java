package com.oddle.app.weatherApp.dao;

import com.oddle.app.weatherApp.entity.WeatherLogEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRepository extends JpaRepository<WeatherLogEntity, Long> {
    List<WeatherLogEntity> findAllByCityNameAndLogDate(String cityName, String logDate, Pageable pageable);
}
