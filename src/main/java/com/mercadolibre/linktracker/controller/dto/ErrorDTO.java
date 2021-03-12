package com.mercadolibre.linktracker.controller.dto;


import com.mercadolibre.linktracker.exception.ResponseEntityException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorDTO {
    String mensaje;
    HttpStatus httpStatus;

    public ErrorDTO(ResponseEntityException ex) {
        this.mensaje = ex.getMessage();
        this.httpStatus = ex.getHttpStatus();
    }
}
