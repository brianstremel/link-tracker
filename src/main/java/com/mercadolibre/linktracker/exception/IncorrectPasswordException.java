package com.mercadolibre.linktracker.exception;

import org.springframework.http.HttpStatus;

public class IncorrectPasswordException extends ResponseEntityException {
    public IncorrectPasswordException(String text) {
        super(HttpStatus.FORBIDDEN, "Acceso prohibido: " + text);
    }
}
