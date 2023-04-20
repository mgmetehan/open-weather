package com.mgmetehan.openweather.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgmetehan.openweather.model.Weather;
import com.mgmetehan.openweather.repository.WeatherRepository;
import com.mgmetehan.openweather.service.WeatherService;
import com.mgmetehan.openweather.shared.exception.ErrorResponse;
import com.mgmetehan.openweather.shared.exception.WeatherStackApiException;
import com.mgmetehan.openweather.shared.model.dto.WeatherDto;
import com.mgmetehan.openweather.shared.model.resource.WeatherResponse;
import com.mgmetehan.openweather.shared.util.DateUtil;
import com.mgmetehan.openweather.shared.util.UrlUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Clock;
import java.util.Optional;

import static com.mgmetehan.openweather.config.WeatherConfigurationProperties.API_CALL_LIMIT;

@Service
@RequiredArgsConstructor
@Slf4j
@CacheConfig(cacheNames = {"weathers"})
public class WeatherServiceImpl implements WeatherService {
    private final WeatherRepository weatherRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @CacheEvict(allEntries = true)
    @PostConstruct
    @Scheduled(fixedRateString = "${weather-stack.cache-ttl}")
    public void clearCache() {
        log.info("Clearing cache");
    }

    @Override
    @Cacheable(key = "#city")
    public WeatherDto getWeather(String city) {
        Optional<Weather> weatherOptional = weatherRepository.findFirstByRequestedCityNameOrderByUpdatedTimeDesc(city);

        return weatherOptional.map(weather -> {
            if (weather.getUpdatedTime().isBefore(DateUtil.getLocalDateTimeNow().minusMinutes(API_CALL_LIMIT))) {
                log.info(String.format("Creating a new city weather from weather stack api for %s due to the current one is not up-to-date", city));
                return createCityWeather(city);
            }
            log.info(String.format("Getting weather from database for %s due to it is already up-to-date", city));
            return WeatherDto.convert(weather);
        }).orElseGet(() -> {
            log.info(String.format("Creating a new city weather from weather stack api for %s due to it is not in database", city));
            return createCityWeather(city);
        });
    }

    @CachePut(key = "#city")
    public WeatherDto createCityWeather(String city) {
        log.info("Requesting weather stack api for city: " + city);
        var url = UrlUtil.getWeatherStackUrl(city);
        var response = restTemplate.getForEntity(url, String.class);

        try {
            WeatherResponse weatherResponse = objectMapper.readValue(response.getBody(), WeatherResponse.class);
            return WeatherDto.convert(saveWeather(city, weatherResponse));
        } catch (JsonProcessingException e) {
            try {
                ErrorResponse errorResponse = objectMapper.readValue(response.getBody(), ErrorResponse.class);
                throw new WeatherStackApiException(errorResponse);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private Weather saveWeather(String city, WeatherResponse weatherResponse) {
        var responseLocalTime = weatherResponse.location().localtime();
        var time = DateUtil.localDateTimeFormat(responseLocalTime);

        var updatedTime = DateUtil.getLocalDateTimeNow();

        var weather = Weather.builder()
                .requestedCityName(city)
                .cityName(weatherResponse.location().name())
                .country(weatherResponse.location().country())
                .temperature(weatherResponse.current().temperature())
                .updatedTime(updatedTime)
                .responseDateTime(time)
                .build();

        return weatherRepository.save(weather);
    }
}



