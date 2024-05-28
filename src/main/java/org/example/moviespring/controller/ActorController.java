package org.example.moviespring.controller;

import org.example.moviespring.model.Actor;
import org.example.moviespring.service.ActorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/getActor")
    public List<Actor> getActors() {
        return actorService.getActors();
    }
}
