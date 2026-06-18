package com.fiesta.invitaciones.controller;

import com.fiesta.invitaciones.dto.DashboardResponse;
import com.fiesta.invitaciones.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/admin/rsvp")
    public DashboardResponse dashboard(){

        return dashboardService
                .getDashboard();

    }

}