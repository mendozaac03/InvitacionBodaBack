package com.fiesta.invitaciones.controller;

import com.fiesta.invitaciones.mongo.SongSuggestion;
import com.fiesta.invitaciones.repository.SongSuggestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@Slf4j
public class SongController {

    @Autowired
    private SongSuggestionRepository repository;

    @PostMapping("/song")
    public SongSuggestion saveSong(
            @RequestBody SongSuggestion request){
        System.out.println("valor del usuario actual-------------------->" + request.getUsuarioActual());
        log.info("valor del usuario actual-------------------->",request.getUsuarioActual());
        return repository.save(request);
    }

}