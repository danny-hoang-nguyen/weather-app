package com.oddle.app.weatherApp.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class LogNotFoundException extends RuntimeException {


    private static final long serialVersionUID = 1L;
    private String message;

    public LogNotFoundException(String message) {
        this.message = message;
    }

  public String getMessage() {
        return this.message;
  }
}
