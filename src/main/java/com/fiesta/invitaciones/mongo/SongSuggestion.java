package com.fiesta.invitaciones.mongo;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "songs")
@Getter
@Setter
public class SongSuggestion {

    @Id
    private String id;

    private String guestName;
    private String songName;
    private String usuarioActual;

}
