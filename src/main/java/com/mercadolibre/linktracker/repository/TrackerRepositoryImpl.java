package com.mercadolibre.linktracker.repository;

import com.mercadolibre.linktracker.exception.LinkNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class TrackerRepositoryImpl implements TrackerRepository {

    private Map<String, LinkDAO> repositorio = new HashMap<>();


    @Override
    public void agregarLink(LinkDAO dao, String key) {
        repositorio.put(key, dao);
    }

    @Override
    public LinkDAO buscarLink(String key) throws LinkNotFoundException {
        LinkDAO link = repositorio.get(key);
        if(link == null) {
            throw new LinkNotFoundException("No se encontró key: " + key);
        }
        return link;
    }

    @Override
    public void eliminarLink(String linkid) throws LinkNotFoundException {
        if(repositorio.remove(linkid) == null) {
            throw new LinkNotFoundException("No se encontró key a eliminar: " + linkid);
        }
    }
}
