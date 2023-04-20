package com.mgmetehan.openweather.shared.exception;

public record RestTemplateError(
        String timestamp,
        String status,
        String error,
        String path
) {
}
