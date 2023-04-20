package com.mgmetehan.openweather.repository;

import com.mgmetehan.openweather.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather, String> {
    //*** Bu method en son güncellenen tek veriyi getirir.
    Optional<Weather> findFirstByRequestedCityNameOrderByUpdatedTimeDesc(String city);
}
