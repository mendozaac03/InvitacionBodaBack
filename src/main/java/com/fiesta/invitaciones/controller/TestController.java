package com.fiesta.invitaciones.controller;


import com.fiesta.invitaciones.mongo.Photo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {


    @GetMapping("/hola")
    public String like() {
        log.info("entre a revisar**********************************");
        return "hola perro";
    }
}
