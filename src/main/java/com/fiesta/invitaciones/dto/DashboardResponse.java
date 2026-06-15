package com.fiesta.invitaciones.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DashboardResponse {
    private long total;
    private long confirmados;
    private long ingresados;
}
