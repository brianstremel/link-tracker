package com.mercadolibre.linktracker.repository;

import com.mercadolibre.linktracker.exception.LinkNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class TrackerRepositoryImpl implements TrackerRepository {

    private Map<String, LinkDAO> repository = new HashMap<>();


    @Override
    public void addLink(LinkDAO dao, String key) {
        repository.put(key, dao);
    }

    @Override
    public LinkDAO searchLink(String key) throws LinkNotFoundException {
        LinkDAO link = repository.get(key);
        if(link == null) {
            throw new LinkNotFoundException("Key not found: " + key);
        }
        return link;
    }

    @Override
    public void deleteLink(String linkid) throws LinkNotFoundException {
        if(repository.remove(linkid) == null) {
            throw new LinkNotFoundException("Key not found: " + linkid);
        }
    }
}
