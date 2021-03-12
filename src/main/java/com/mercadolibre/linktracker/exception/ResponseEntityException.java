package com.mercadolibre.linktracker.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ResponseEntityException extends Exception {
    private HttpStatus httpStatus;

    public ResponseEntityException(HttpStatus httpStatus, String text) {
        super(text);
        this.httpStatus = httpStatus;
    }
}
