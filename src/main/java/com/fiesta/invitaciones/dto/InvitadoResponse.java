package com.fiesta.invitaciones.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class InvitadoResponse {

    private UUID id;
    private String nombre;
    private int invitadosPermitidos;
    private String estatus;
    private boolean tieneQr;
}
