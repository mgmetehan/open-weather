package com.mgmetehan.openweather.controller;

import com.mgmetehan.openweather.service.WeatherService;
import com.mgmetehan.openweather.shared.endpoints.WeatherEndpoints;
import com.mgmetehan.openweather.shared.model.dto.WeatherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(WeatherEndpoints.WEATHER)
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("/{city}")
    public ResponseEntity<WeatherDto> getWeather(@PathVariable("city") String city) {
        return ResponseEntity.status(HttpStatus.OK).body(weatherService.getWeather(city));
    }

}
