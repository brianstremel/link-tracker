package com.mercadolibre.linktracker.service;

import com.mercadolibre.linktracker.controller.dto.CreatedLinkDTO;
import com.mercadolibre.linktracker.controller.dto.LinkDTO;
import com.mercadolibre.linktracker.controller.dto.LinkStatusDTO;
import com.mercadolibre.linktracker.exception.IncorrectPasswordException;
import com.mercadolibre.linktracker.exception.LinkNotFoundException;
import com.mercadolibre.linktracker.repository.LinkDAO;
import com.mercadolibre.linktracker.repository.TrackerRepository;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class TrackerServiceImpl implements TrackerService {

    @Autowired
    private TrackerRepository trackerRepository;


    private void validateUrl(String url) throws LinkNotFoundException {
        String[] schemes = {"http","https"};
        UrlValidator urlValidator = new UrlValidator(schemes);
        if (!urlValidator.isValid(url)) {
            throw new LinkNotFoundException("Url inválida: " + url);
        }
    }

    @Override
    public CreatedLinkDTO createLink(LinkDTO linkDTO) throws LinkNotFoundException {
        String key = UUID.randomUUID().toString();
        this.validateUrl(linkDTO.getUrl());
        LinkDAO linkDAO = new LinkDAO(linkDTO.getUrl(), linkDTO.getPassword());
        trackerRepository.addLink(linkDAO, key);
        return new CreatedLinkDTO(linkDTO.getUrl(), key);
    }

    @Override
    public String accessLink(String linkId, String password) throws LinkNotFoundException, IncorrectPasswordException {
        LinkDAO linkDAO = trackerRepository.searchLink(linkId);
        if(linkDAO.getPassword() != null && !linkDAO.getPassword().equals(password)) {
            throw new IncorrectPasswordException("Id:" + linkId);
        }
        linkDAO.addAccess();
        return linkDAO.getUrl();
    }

    @Override
    public LinkStatusDTO watchStatistics(String linkId) throws LinkNotFoundException {
        LinkDAO linkDAO = trackerRepository.searchLink(linkId);
        return new LinkStatusDTO(linkDAO.getUrl(), linkDAO.getAccessCounter());
    }

    @Override
    public void invalidateAccess(String linkId) throws LinkNotFoundException {
        trackerRepository.deleteLink(linkId);
    }
}
