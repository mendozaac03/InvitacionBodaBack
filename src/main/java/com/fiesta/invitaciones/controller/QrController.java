package com.fiesta.invitaciones.controller;

import com.fiesta.invitaciones.mongo.Guest;
import com.fiesta.invitaciones.mongo.Photo;
import com.fiesta.invitaciones.mongo.Rsvp;
import com.fiesta.invitaciones.repository.GuestRepository;
import com.fiesta.invitaciones.repository.PhotoRepository;
import com.fiesta.invitaciones.repository.RsvpRepository;
import com.fiesta.invitaciones.service.JwtService;
import com.fiesta.invitaciones.service.QrService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/qr")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class QrController {

    private final JwtService jwtService;
    private final QrService qrService;
    private final GuestRepository invitadoRepository;
    private final RsvpRepository eventRepository;
    private final PhotoRepository photoRepository;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> generar(@PathVariable String id) throws Exception {

        Guest invitado = invitadoRepository.findByCode(id).orElseThrow();

        String token = jwtService.generateToken(id, invitado.getEventId(), invitado.getAllowedGuests());

        byte[] qr = qrService.generate(token);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(qr);
    }


    // Crear evento + QR
    @PostMapping("/event")
    public ResponseEntity<byte[]> createEvent() throws Exception {
        Rsvp event = new Rsvp();
        event.setName("Boda Tani & ABG ");

        event = eventRepository.save(event);

        String url = "file:///C:/Users/ABG/OneDrive/Documentos/proyectos/invitacion/front/"+ event.getId()+"principal.html";
        //event.setQrCode(url);
        eventRepository.save(event);
        byte[] qr =qrService.generateQR(url);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(qr);
    }

    // Página evento
    @GetMapping("/event/{id}")
    public ModelAndView eventPage(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("eventId", id);
        return mv;
    }

    // Subir foto
    @PostMapping("/upload")
    public String upload(@RequestParam Long eventId, @RequestParam String category,@RequestParam MultipartFile file) throws Exception {
        try {
            String folder = "D://invitations/uploads/";
            //file.transferTo(new File(path));
            File dir = new File(folder);
            if (!dir.exists()) dir.mkdirs();

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String path = folder + fileName;

            file.transferTo(new File(path));

            Photo photo = new Photo();
            photo.setEventId(eventId);
            photo.setCategory(category);
            photo.setUrl(path);

            photoRepository.save(photo);
        }catch (Exception e){
            log.error("error al subir el archivo",e);
        }


        return "ok";
    }

    @GetMapping("/photos")
    public List<Photo> getPhotos(
            @RequestParam Long eventId,
            @RequestParam String category
    ) {
        return photoRepository.findByEventIdAndCategory(eventId, category);
    }

    @PostMapping("/like/{id}")
    public Photo like(@PathVariable String id) {
        Photo photo = photoRepository.findById(id).orElseThrow();
        photo.setLikes(photo.getLikes() + 1);
        return photoRepository.save(photo);
    }
}