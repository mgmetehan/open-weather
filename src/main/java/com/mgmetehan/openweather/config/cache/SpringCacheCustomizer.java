package com.mgmetehan.openweather.config.cache;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.mgmetehan.openweather.config.WeatherConfigurationProperties.WEATHER_CACHE_NAME;

@Component
public class SpringCacheCustomizer implements CacheManagerCustomizer<ConcurrentMapCacheManager> {

    @Override
    public void customize(ConcurrentMapCacheManager cacheManager) {
        cacheManager.setCacheNames(List.of(WEATHER_CACHE_NAME));
        cacheManager.setAllowNullValues(false);
    }
}