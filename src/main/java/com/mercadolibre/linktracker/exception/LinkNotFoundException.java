package com.mercadolibre.linktracker.exception;

import org.springframework.http.HttpStatus;

public class LinkNotFoundException extends ResponseEntityException {
    public LinkNotFoundException(String string) {
        super(HttpStatus.NOT_FOUND, "No se encontró: " + string);
    }
}
