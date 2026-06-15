package com.fiesta.invitaciones.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidacionRequest {
    private String token;
    private int cantidad;
}
