package org.example.moviespring.controller;


import org.example.moviespring.DTOs.ActorDTO;
import org.example.moviespring.DTOs.DTOConverter;
import org.example.moviespring.DTOs.MovieDTO;
import org.example.moviespring.model.Actor;
import org.example.moviespring.model.Movie;
import org.example.moviespring.service.ActorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/getActors")
    public List<ActorDTO> getActors() {
        return actorService.getActors().stream().map(DTOConverter::convertToActorDTO).toList();
    }

    @GetMapping("/getActor/{id}")
    public ResponseEntity<ActorDTO> getActor(@PathVariable Long id) {
        Optional<Actor> actor = actorService.getActorById(id);
        if (actor.isPresent()) {
            ActorDTO actorDTO = DTOConverter.convertToActorDTO(actor.get());
            return ResponseEntity.ok(actorDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/getActorByName/{name}")
    public ResponseEntity<ActorDTO> getActorByTitle(@PathVariable String name) {
        Optional<Actor> foundActor = actorService.getActorByTitle(name);
        return foundActor
                .map(actor -> ResponseEntity.status(HttpStatus.OK).body(DTOConverter.convertToActorDTO(actor)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/addActor")
    public ResponseEntity<ActorDTO> addActor(@RequestBody Actor actor) {
        Optional<Actor> foundActor = actorService.getActorByTitle(actor.getName());
        return foundActor
                .map(a -> ResponseEntity.status(HttpStatus.CONFLICT).<ActorDTO>build())
                .orElseGet(() -> {
                    actorService.addActor(actor);
                    return ResponseEntity.status(HttpStatus.CREATED).body(DTOConverter.convertToActorDTO(actor));
                });
    }

    @DeleteMapping("/deleteActor/{id}")
    public ResponseEntity<ActorDTO> deleteActor(@PathVariable Long id) {
        Optional<Actor> foundActor = actorService.deleteActorById(id);
        return foundActor
                .map(a -> ResponseEntity.status(HttpStatus.OK).body(DTOConverter.convertToActorDTO(a)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}

