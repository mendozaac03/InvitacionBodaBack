package com.fiesta.invitaciones.repository;


import com.fiesta.invitaciones.mongo.Rsvp;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

public interface RsvpRepository extends MongoRepository<Rsvp, String> {

    Optional<Rsvp> findByCode(String code);
}
