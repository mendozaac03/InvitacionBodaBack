package com.fiesta.invitaciones.repository;

import com.fiesta.invitaciones.mongo.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends MongoRepository<Photo, String> {
    List<Photo> findByEventId(Long eventId);

    List<Photo> findByEventIdAndCategory(Long eventId, String category);
}