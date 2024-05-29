package org.example.moviespring.controller;

import org.example.moviespring.model.Actor;
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
    public List<Actor> getActors() {
        return actorService.getActors();
    }

    @GetMapping("/getActor/{id}")
    public ResponseEntity<Actor> getActor(@PathVariable Long id) {
        Optional<Actor> foundActor = actorService.getActorById(id);
        return foundActor
                .map(a -> ResponseEntity.status(HttpStatus.OK).body(a))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/getActorByName/{name}")
    public ResponseEntity<Actor> getActorByTitle(@PathVariable String name) {
        Optional<Actor> foundActor = actorService.getActorByTitle(name);
        return foundActor
                .map(actor -> ResponseEntity.status(HttpStatus.OK).body(actor))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/addActor")
    public ResponseEntity<Actor> addActor(@RequestBody Actor actor) {
        Optional<Actor> foundActor = actorService.getActorByTitle(actor.getName());
        return foundActor
                .map(a -> ResponseEntity.status(HttpStatus.CONFLICT).<Actor>build())
                .orElseGet(() -> {
                    actorService.addActor(actor);
                    return ResponseEntity.status(HttpStatus.CREATED).body(actor);
                });
    }

    @DeleteMapping("/deleteActor/{id}")
    public ResponseEntity<Actor> deleteActor(@PathVariable Long id) {
        Optional<Actor> foundActor = actorService.deleteActorById(id);
        System.out.println("Hej där");
        return foundActor
                .map(a -> ResponseEntity.status(HttpStatus.OK).body(foundActor.get()))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}

