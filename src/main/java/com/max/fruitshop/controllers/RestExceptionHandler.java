package com.max.fruitshop.controllers;

import com.max.fruitshop.exceptions.RestException;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({RestException.class})
    public ResponseEntity<ErrorMessage> restException(Exception ex) {
        RestException restException = (RestException) ex;
        return new ResponseEntity<>(new ErrorMessage(restException), restException.getStatus());
    }

    @Data
    private class ErrorMessage {
        private int status;
        private String message;

        ErrorMessage(RestException ex) {
            status = ex.getStatus().value();
            message = ex.getMessage();
        }
    }

}
