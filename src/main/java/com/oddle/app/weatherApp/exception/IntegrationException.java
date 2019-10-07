package com.oddle.app.weatherApp.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class IntegrationException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String message;

    public IntegrationException(String message) {
        super(message, null, false, false);
        this.message = message;
    }
}