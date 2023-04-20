package com.mgmetehan.openweather.shared.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
public class RestServiceException extends RuntimeException {

    private String serviceName;
    private HttpStatus statusCode;
    private String error;
}

