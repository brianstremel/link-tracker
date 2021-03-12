package com.mercadolibre.linktracker.repository;

import com.mercadolibre.linktracker.exception.LinkNotFoundException;


public interface TrackerRepository {

    public void agregarLink(LinkDAO dao, String key);
    public LinkDAO buscarLink(String key) throws LinkNotFoundException;
    public void eliminarLink(String linkid) throws LinkNotFoundException;
}
