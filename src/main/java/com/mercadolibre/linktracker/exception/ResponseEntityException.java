package com.mercadolibre.linktracker.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ResponseEntityException extends Exception {
    private HttpStatus httpStatus;

    public ResponseEntityException(HttpStatus httpStatus, String texto) {
        super(texto);
        this.httpStatus = httpStatus;
    }
}
