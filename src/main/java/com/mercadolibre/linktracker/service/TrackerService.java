package com.mercadolibre.linktracker.service;

import com.mercadolibre.linktracker.controller.dto.CreatedLinkDTO;
import com.mercadolibre.linktracker.controller.dto.LinkDTO;
import com.mercadolibre.linktracker.controller.dto.LinkStatusDTO;
import com.mercadolibre.linktracker.exception.LinkNotFoundException;
import com.mercadolibre.linktracker.exception.IncorrectPasswordException;

import java.net.MalformedURLException;

public interface TrackerService {
    public CreatedLinkDTO createLink(LinkDTO linkDTO) throws MalformedURLException, LinkNotFoundException;
    public String accessLink(String linkid, String password) throws LinkNotFoundException, IncorrectPasswordException;
    public LinkStatusDTO watchStatistics(String linkid) throws LinkNotFoundException;
    void invalidateAccess(String linkid) throws LinkNotFoundException;
}
