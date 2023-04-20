package com.mgmetehan.openweather.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WeatherConfigurationProperties {
    public static String WEATHER_STACK_API_BASE_URL;
    public static final String WEATHER_STACK_API_ACCESS_KEY_PARAM = "?access_key=";
    public static final String WEATHER_STACK_API_QUERY_PARAM = "&query=";
    public static String API_KEY;
    public static String WEATHER_CACHE_NAME;
    public static Integer API_CALL_LIMIT;


    @Value("${weather-stack.api-url}")
    public void setWeatherStackApiBaseUrl(String apiUrl) {
        WeatherConfigurationProperties.WEATHER_STACK_API_BASE_URL = apiUrl;
    }

    @Value("${weather-stack.api-key}")
    public void setApiKey(String apiKey) {
        WeatherConfigurationProperties.API_KEY = apiKey;
    }

    @Value("${weather-stack.cache-name}")
    public void setWeatherCacheName(String cacheName) {
        WeatherConfigurationProperties.WEATHER_CACHE_NAME = cacheName;
    }

    @Value("${weather-stack.api-call-limit}")
    public void setApiCallLimit(Integer apiCallLimit) {
        WeatherConfigurationProperties.API_CALL_LIMIT = apiCallLimit;
    }
}
