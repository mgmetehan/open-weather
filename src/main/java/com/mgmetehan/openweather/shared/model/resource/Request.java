package com.mgmetehan.openweather.shared.model.resource;

//!!! record sinifinin icinde getter, setter ve toString metotlari otomatik olarak olusturulur.
public record Request(
        String type,
        String query,
        String language,
        String unit
) {
}
