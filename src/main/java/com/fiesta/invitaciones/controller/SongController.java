package com.fiesta.invitaciones.controller;

import com.fiesta.invitaciones.mongo.SongSuggestion;
import com.fiesta.invitaciones.repository.SongSuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class SongController {

    @Autowired
    private SongSuggestionRepository repository;

    @PostMapping("/song")
    public SongSuggestion saveSong(
            @RequestBody SongSuggestion request){

        return repository.save(request);
    }

}