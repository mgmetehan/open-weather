package com.mgmetehan.openweather.shared.endpoints;

import com.mgmetehan.openweather.shared.endpoints.base.BaseEndpoints;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WeatherEndpoints extends BaseEndpoints {
    public static final String WEATHER = BASE + "/weather";
}
