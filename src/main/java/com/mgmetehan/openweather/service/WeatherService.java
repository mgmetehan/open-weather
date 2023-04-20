package com.mgmetehan.openweather.service;

import com.mgmetehan.openweather.shared.model.dto.WeatherDto;

public interface WeatherService {
    WeatherDto getWeather(String city);
}
