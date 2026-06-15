package com.fiesta.invitaciones.controller;

import com.fiesta.invitaciones.mongo.Guest;
import com.fiesta.invitaciones.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/guest")
public class GuestController {

    @Autowired
    private GuestRepository repository;

    @GetMapping("/{code}")
    public Guest getGuest(@PathVariable String code){
        Optional<Guest> invitado =repository.findByCode(code);

        return invitado.isPresent() ? invitado.get(): Guest.builder().code("-----").build();

    }

    @PostMapping(
            value = "/import",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> importGuests(
            @RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Archivo vacío");
        }

        List<Guest> guests = new ArrayList<>();

        try (
                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(
                                        file.getInputStream(),
                                        StandardCharsets.UTF_8))
        ) {

            String line;

            while ((line = reader.readLine()) != null) {

                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");

                if (data.length < 3) {
                    continue;
                }

                Guest guest = new Guest();

                guest.setFamilyName(data[0].trim());
                guest.setPhone(data[1].trim());
                guest.setAllowedGuests(
                        Integer.parseInt(data[2].trim()));

                guest.setCode(generateCode());

//                guest.setConfirmed(false);
                guest.setLigaInvitacion("https://tuboda.com/?code=" + guest.getCode());
                guest.setEventId("BodaTani&Abg");
                guests.add(guest);
            }

            repository.saveAll(guests);

            return ResponseEntity.ok(
                    Map.of(
                            "inserted", guests.size(),
                            "message", "Invitados cargados correctamente"
                    ));

        } catch (Exception e) {

            return ResponseEntity.internalServerError()
                    .body(e.getMessage());
        }
    }

    private String generateCode() {

        return UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 8)
                .toUpperCase();
    }
}
