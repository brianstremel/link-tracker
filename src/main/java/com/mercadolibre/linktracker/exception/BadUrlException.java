package com.mercadolibre.linktracker.exception;

import org.springframework.http.HttpStatus;

public class BadUrlException extends ResponseEntityException {

    public BadUrlException(HttpStatus httpStatus, String texto) {
        super(httpStatus, texto);
    }
}
