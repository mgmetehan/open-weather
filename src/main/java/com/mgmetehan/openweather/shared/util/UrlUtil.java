package com.mgmetehan.openweather.shared.util;

import com.mgmetehan.openweather.config.WeatherConfigurationProperties;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class UrlUtil {
    private final WeatherConfigurationProperties weatherConfigurationProperties = null;

    public String getWeatherStackUrl(String city) {
      log.info(weatherConfigurationProperties.WEATHER_STACK_API_BASE_URL +
              weatherConfigurationProperties.WEATHER_STACK_API_ACCESS_KEY_PARAM +
              weatherConfigurationProperties.API_KEY +
              weatherConfigurationProperties.WEATHER_STACK_API_QUERY_PARAM +
              city);
        return weatherConfigurationProperties.WEATHER_STACK_API_BASE_URL +
                weatherConfigurationProperties.WEATHER_STACK_API_ACCESS_KEY_PARAM +
                weatherConfigurationProperties.API_KEY +
                weatherConfigurationProperties.WEATHER_STACK_API_QUERY_PARAM +
                city;
    }
}
