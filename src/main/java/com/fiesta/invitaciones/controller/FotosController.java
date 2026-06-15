package com.fiesta.invitaciones.controller;


import com.fiesta.invitaciones.mongo.Photo;
import com.fiesta.invitaciones.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invitados")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FotosController {

    private final PhotoRepository photoRepository;



    @PostMapping("/like/{id}")
    public Photo like(@PathVariable String id) {
        Photo photo = photoRepository.findById(id).orElseThrow();
        photo.setLikes(photo.getLikes() + 1);
        return photoRepository.save(photo);
    }

    @PostMapping("/comment/{id}")
    public Photo comment(@PathVariable String id, @RequestParam String text) {
        Photo photo = photoRepository.findById(id).orElseThrow();
        photo.getComments().add(text);
        return photoRepository.save(photo);
    }
}
