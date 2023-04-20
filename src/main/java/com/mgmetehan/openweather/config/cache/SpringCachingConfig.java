package com.mgmetehan.openweather.config.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.mgmetehan.openweather.config.WeatherConfigurationProperties.WEATHER_CACHE_NAME;

@Configuration
@EnableCaching
public class SpringCachingConfig {
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(WEATHER_CACHE_NAME);
    }

}
