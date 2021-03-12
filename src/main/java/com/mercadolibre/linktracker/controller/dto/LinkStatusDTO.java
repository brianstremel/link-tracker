package com.mercadolibre.linktracker.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor @AllArgsConstructor
public class LinkStatusDTO {
    private String url;
    private Integer contador;
}
