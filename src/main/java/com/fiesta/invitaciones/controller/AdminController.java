package com.fiesta.invitaciones.controller;

import com.fiesta.invitaciones.dto.DashboardResponse;
import com.fiesta.invitaciones.repository.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final GuestRepository invitadoRepository;

    @GetMapping("/dashboard/{eventoId}")
    public DashboardResponse dashboard(@PathVariable UUID eventoId) {

        return new DashboardResponse(0, 0, 0);
    }


}
