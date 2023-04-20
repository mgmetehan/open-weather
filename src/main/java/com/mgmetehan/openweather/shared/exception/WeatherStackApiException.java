package com.mgmetehan.openweather.shared.exception;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class WeatherStackApiException extends RuntimeException {
    private ErrorResponse errorResponse;

}