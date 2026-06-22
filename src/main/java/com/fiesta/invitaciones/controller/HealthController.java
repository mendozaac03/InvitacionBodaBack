package com.fiesta.invitaciones.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@CrossOrigin
public class HealthController {

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
