package com.fiesta.invitaciones.repository;

import com.fiesta.invitaciones.mongo.Guest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GuestRepository extends MongoRepository<Guest, String> {

    Optional<Guest> findByCode(String code);

}
