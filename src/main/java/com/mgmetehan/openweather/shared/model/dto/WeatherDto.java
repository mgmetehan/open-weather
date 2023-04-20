package com.mgmetehan.openweather.shared.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mgmetehan.openweather.model.Weather;

import java.time.LocalDateTime;

//!!! record sinifinin icinde getter, setter ve toString metotlari otomatik olarak olusturulur.
public record WeatherDto(
        String cityName,
        String country,
        Integer temperature,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime updatedTime
) {
    public static WeatherDto convert(Weather from) {
        return new WeatherDto(
                from.getCityName(),
                from.getCountry(),
                from.getTemperature(),
                from.getUpdatedTime());
    }
}
