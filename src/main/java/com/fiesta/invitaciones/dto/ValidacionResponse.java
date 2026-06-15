package com.fiesta.invitaciones.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidacionResponse {
    private String resultado;
    private String nombre;
    private int restantes;

}