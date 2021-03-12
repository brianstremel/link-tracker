package com.mercadolibre.linktracker.service;

import com.mercadolibre.linktracker.controller.dto.LinkCreadoDTO;
import com.mercadolibre.linktracker.controller.dto.LinkDTO;
import com.mercadolibre.linktracker.controller.dto.LinkStatusDTO;
import com.mercadolibre.linktracker.exception.LinkNotFoundException;
import com.mercadolibre.linktracker.exception.IncorrectPasswordException;

import java.net.MalformedURLException;

public interface TrackerService {
    public LinkCreadoDTO crearLink(LinkDTO linkDTO) throws MalformedURLException, LinkNotFoundException;
    public String accederLink(String linkid, String password) throws LinkNotFoundException, IncorrectPasswordException;
    public LinkStatusDTO verEstadistica(String linkid) throws LinkNotFoundException;
    void invalidarAcceso(String linkid) throws LinkNotFoundException;
}
