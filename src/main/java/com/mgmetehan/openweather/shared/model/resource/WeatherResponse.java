package com.mgmetehan.openweather.shared.model.resource;

//!!! record sinifinin icinde getter, setter ve toString metotlari otomatik olarak olusturulur.
public record WeatherResponse(
        Request request,
        Location location,
        Current current
) {
}