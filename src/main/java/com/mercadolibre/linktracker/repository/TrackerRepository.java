package com.mercadolibre.linktracker.repository;

import com.mercadolibre.linktracker.exception.LinkNotFoundException;


public interface TrackerRepository {

    public void addLink(LinkDAO dao, String key);
    public LinkDAO searchLink(String key) throws LinkNotFoundException;
    public void deleteLink(String linkid) throws LinkNotFoundException;
}
