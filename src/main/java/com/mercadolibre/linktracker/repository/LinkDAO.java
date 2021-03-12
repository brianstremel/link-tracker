package com.mercadolibre.linktracker.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LinkDAO {
    private String url;
    private String password;
    private Integer accessCounter;

    public LinkDAO(String url, String password) {
        this.url = url;
        this.password = password;
        this.accessCounter = 0;
    }

    public void addAccess() {
        accessCounter++;
    }
}
