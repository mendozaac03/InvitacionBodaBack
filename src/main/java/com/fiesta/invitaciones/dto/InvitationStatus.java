package com.fiesta.invitaciones.dto;

import lombok.Data;

@Data
public class InvitationStatus {

    private String familyName;
    private Integer allowedGuests;
    private Integer guestsAttending;
    private Boolean confirmed;
    private String comment;

}
