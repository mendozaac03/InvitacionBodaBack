package com.fiesta.invitaciones.controller;


import com.fiesta.invitaciones.mongo.Photo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @GetMapping("/hola")
    public String like() {
        return "hola perro";
    }
}
