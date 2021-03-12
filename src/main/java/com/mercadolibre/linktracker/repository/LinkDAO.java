package com.mercadolibre.linktracker.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LinkDAO {
    private String url;
    private String password;
    private Integer cantidadAccesos;

    public LinkDAO(String url, String password) {
        this.url = url;
        this.password = password;
        this.cantidadAccesos = 0;
    }

    public void aumentarAcceso() {
        cantidadAccesos ++;
    }
}
