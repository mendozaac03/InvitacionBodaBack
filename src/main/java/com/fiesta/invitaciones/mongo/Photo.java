package com.fiesta.invitaciones.mongo;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "photos")
@Getter
@Setter
public class Photo {

    @Id
    private String id;

    private Long eventId;
    private String category;
    private String url;

    private int likes = 0;

    private List<String> comments = new ArrayList<>();
}
