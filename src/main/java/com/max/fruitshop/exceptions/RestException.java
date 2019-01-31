package com.max.fruitshop.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class RestException extends RuntimeException {

    private HttpStatus status;
    private String message;

}
