package com.fiesta.invitaciones.mongo;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "guests")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Guest {

    @Id
    private String id;

    private String code;
    private String familyName;
    private Integer allowedGuests;
    private String eventId;
    private Integer confirmedGuests;
    private String phone;
    private String ligaInvitacion;
    private Integer guestsAttending;

    private String comment;

    private Date confirmationDate;

}