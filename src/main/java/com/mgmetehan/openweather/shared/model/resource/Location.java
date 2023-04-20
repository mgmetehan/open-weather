package com.mgmetehan.openweather.shared.model.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

//!!! record sinifinin icinde getter, setter ve toString metotlari otomatik olarak olusturulur.
public record Location(
        String name,
        String country,
        String region,
        Double lat,
        Double lon,
        @JsonProperty("timezone_id")
        String timezoneId,
        String localtime,
        @JsonProperty("localtime_epoch")
        String localtimeEpoch,
        @JsonProperty("utc_offset")
        String utcOffset
) {
}
