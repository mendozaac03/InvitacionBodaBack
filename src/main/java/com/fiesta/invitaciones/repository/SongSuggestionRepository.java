package com.fiesta.invitaciones.repository;

import com.fiesta.invitaciones.mongo.SongSuggestion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SongSuggestionRepository
        extends MongoRepository<SongSuggestion, String> {
}
