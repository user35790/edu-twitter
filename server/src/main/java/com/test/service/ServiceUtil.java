package com.test.service;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ServiceUtil {

    static Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> mapCollector = Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                FieldError::getDefaultMessage,
                (p1, p2) -> (p1.contains("empty")) ? p1 : p2
        );
        return bindingResult.getFieldErrors().stream().collect(mapCollector);
    }
}
