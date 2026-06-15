package com.fiesta.invitaciones.controller;



import com.fiesta.invitaciones.mongo.Guest;
import com.fiesta.invitaciones.mongo.Rsvp;
import com.fiesta.invitaciones.repository.GuestRepository;
import com.fiesta.invitaciones.repository.RsvpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/rsvp")
@CrossOrigin("*")
public class RsvpController {

    @Autowired
    private RsvpRepository repository;

    @Autowired
    private GuestRepository guestRepository;

    private static final LocalDate RSVP_DEADLINE =
            LocalDate.of(2026, 7, 22);

    @PostMapping
    public Rsvp saveRsvp(@RequestBody Rsvp request){

        Optional<Rsvp> existing = repository.findByCode(request.getCode());
        Rsvp rsvp;
        if(existing.isPresent()){
            rsvp = existing.get();
        }else{
            rsvp = new Rsvp();
        }
        rsvp.setCode(request.getCode());
        rsvp.setFamilyName(request.getFamilyName());
        rsvp.setAllowedGuests(request.getAllowedGuests());
        rsvp.setGuestsAttending(request.getGuestsAttending());
        rsvp.setComment(request.getComment());
        rsvp.setConfirmationDate(LocalDateTime.now());
        boolean canModify =
                LocalDate.now()
                        .isBefore(RSVP_DEADLINE.plusDays(1));
        rsvp.setCanModify(canModify);
        return repository.save(rsvp);

    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getRsvpByCode(
            @PathVariable String code){

        Optional<Rsvp> rsvpFind =
                repository.findByCode(code);

        if(rsvpFind.isPresent()){

            return ResponseEntity.ok(rsvpFind.get());

        }
        Optional<Guest> guest = guestRepository.findByCode(code);
        Rsvp rsvp = new Rsvp();

        if(guest.isPresent() && !rsvpFind.isPresent()){
            rsvp.setCode(guest.get().getCode());
            rsvp.setFamilyName(guest.get().getFamilyName());
            rsvp.setAllowedGuests(guest.get().getAllowedGuests());
            rsvp.setGuestsAttending(guest.get().getGuestsAttending() == null ? 0 :guest.get().getGuestsAttending());
            rsvp.setComment(guest.get().getComment());
            rsvp.setConfirmationDate(LocalDateTime.now());
            boolean canModify =
                    LocalDate.now()
                            .isBefore(RSVP_DEADLINE.plusDays(1));
            rsvp.setCanModify(canModify);
            repository.save(rsvp);
        }
        return ResponseEntity.ok(rsvp);
    }
}
