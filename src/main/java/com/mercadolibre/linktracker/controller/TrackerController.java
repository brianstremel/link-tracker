package com.mercadolibre.linktracker.controller;


import com.mercadolibre.linktracker.controller.dto.ErrorDTO;
import com.mercadolibre.linktracker.controller.dto.LinkCreadoDTO;
import com.mercadolibre.linktracker.controller.dto.LinkDTO;
import com.mercadolibre.linktracker.controller.dto.LinkStatusDTO;
import com.mercadolibre.linktracker.exception.LinkNotFoundException;
import com.mercadolibre.linktracker.exception.ResponseEntityException;
import com.mercadolibre.linktracker.service.TrackerService;
import com.mercadolibre.linktracker.exception.IncorrectPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class TrackerController {

    @Autowired
    private TrackerService trackerService;

    @PostMapping("/link")
    public ResponseEntity<LinkCreadoDTO> crearLink(@RequestBody LinkDTO linkDTO) throws MalformedURLException, LinkNotFoundException {
        LinkCreadoDTO linkCreadoDTO = trackerService.crearLink(linkDTO);
        return ResponseEntity.ok(linkCreadoDTO);
    }

    @GetMapping("/link/{linkid}")
    public ResponseEntity<String> redirect(@PathVariable String linkid, @RequestParam(required = false) String password)
            throws IncorrectPasswordException, LinkNotFoundException, URISyntaxException {
        String redirectUrl = trackerService.accederLink(linkid, password);
        URI url = new URI(redirectUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(url);
        return new ResponseEntity<>(httpHeaders, HttpStatus.FOUND);
    }

    @GetMapping("/metrics/{linkid}")
    public ResponseEntity<LinkStatusDTO> verEstadistica(@PathVariable String linkid) throws LinkNotFoundException {
        LinkStatusDTO linkStatusDTO = trackerService.verEstadistica(linkid);
        return ResponseEntity.ok(linkStatusDTO);
    }

    @PostMapping("/invalidate/{linkid}")
    public ResponseEntity invalidarAcceso(@PathVariable String linkid) throws LinkNotFoundException {
        trackerService.invalidarAcceso(linkid);
        return new ResponseEntity("", HttpStatus.OK);
    }


    @ExceptionHandler(ResponseEntityException.class)
    public ResponseEntity<ErrorDTO> handleIncorrectPasswordException(ResponseEntityException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(new ErrorDTO(ex));
    }

}
