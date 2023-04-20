package com.mgmetehan.openweather.shared.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CityParameterValidator implements ConstraintValidator<CityNameConstraint, String> {
    @Override
    public void initialize(CityNameConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        value = value.replaceAll("[^a-zA-Z0-9]", "");
        boolean isValid = !StringUtils.isNumeric(value) && !StringUtils.isAllBlank(value);
        if (!isValid) {

            constraintValidatorContext.buildConstraintViolationWithTemplate(value).addConstraintViolation();
            log.info("The city parameter is not valid. value:" + value);
        }
        return !StringUtils.isNumeric(value) && !StringUtils.isAllBlank(value);
    }
}
