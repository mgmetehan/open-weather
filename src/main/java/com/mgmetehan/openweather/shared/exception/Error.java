package com.mgmetehan.openweather.shared.exception;

public record Error (
        String code,
        String type,
        String info
) { }
