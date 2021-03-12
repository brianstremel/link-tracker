package com.mercadolibre.linktracker.service;

import com.mercadolibre.linktracker.controller.dto.LinkCreadoDTO;
import com.mercadolibre.linktracker.controller.dto.LinkDTO;
import com.mercadolibre.linktracker.controller.dto.LinkStatusDTO;
import com.mercadolibre.linktracker.repository.LinkDAO;
import com.mercadolibre.linktracker.repository.TrackerRepository;
import com.mercadolibre.linktracker.exception.LinkNotFoundException;
import com.mercadolibre.linktracker.exception.IncorrectPasswordException;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.UUID;

@Service
public class TrackerServiceImpl implements TrackerService {

    @Autowired
    private TrackerRepository trackerRepository;


    private void validarUrl(String url) throws LinkNotFoundException {
        String[] schemes = {"http","https"};
        UrlValidator urlValidator = new UrlValidator(schemes);
        if (!urlValidator.isValid(url)) {
            throw new LinkNotFoundException("Url inválida: " + url);
        }
    }

    @Override
    public LinkCreadoDTO crearLink(LinkDTO linkDTO) throws LinkNotFoundException {
        String key = UUID.randomUUID().toString();
        this.validarUrl(linkDTO.getUrl());
        LinkDAO linkDAO = new LinkDAO(linkDTO.getUrl(), linkDTO.getPassword());
        trackerRepository.agregarLink(linkDAO, key);
        return new LinkCreadoDTO(linkDTO.getUrl(), key);
    }

    @Override
    public String accederLink(String linkid, String password) throws LinkNotFoundException, IncorrectPasswordException {
        LinkDAO linkDAO = trackerRepository.buscarLink(linkid);
        if(linkDAO.getPassword() != null && !linkDAO.getPassword().equals(password)) {
            throw new IncorrectPasswordException("Id:" + linkid);
        }
        linkDAO.aumentarAcceso();
        return linkDAO.getUrl();
    }

    @Override
    public LinkStatusDTO verEstadistica(String linkid) throws LinkNotFoundException {
        LinkDAO linkDAO = trackerRepository.buscarLink(linkid);
        return new LinkStatusDTO(linkDAO.getUrl(), linkDAO.getCantidadAccesos());
    }

    @Override
    public void invalidarAcceso(String linkid) throws LinkNotFoundException {
        trackerRepository.eliminarLink(linkid);
    }
}
