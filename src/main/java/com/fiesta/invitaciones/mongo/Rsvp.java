package com.fiesta.invitaciones.mongo;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "rsvps")
@Getter
@Setter
public class Rsvp {

    @Id
    private String id;
    private String code;
    private String attendance;
    private String name;
    private String comment;
    private String familyName;
    private Integer allowedGuests;
    private Integer guestsAttending;
    private LocalDateTime ConfirmationDate;
    private boolean canModify;



}
