package com.mgmetehan.openweather.shared.exception;

public record ErrorResponse(
        String success,
        Error error) {
}
