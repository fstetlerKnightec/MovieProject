package org.example.moviespring.service;

import org.example.moviespring.model.Actor;
import org.example.moviespring.repo.ActorRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorService {

    private final ActorRepo actorRepo;


    public ActorService(ActorRepo actorRepo) {
        this.actorRepo = actorRepo;
    }

    public Optional<Actor> getActorById(Long id) {
        return actorRepo.findById(id);
    }

    public List<Actor> getActors() {
        return actorRepo.findAll();
    }
}
