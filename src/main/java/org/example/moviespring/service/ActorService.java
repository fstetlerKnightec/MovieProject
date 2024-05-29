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

    public List<Actor> getActors() {
        return actorRepo.findAll();
    }

    public Optional<Actor> getActorById(Long id) {
        return actorRepo.findById(id);
    }

    public Optional<Actor> getActorByTitle(String title) {
        return actorRepo.findByNameIgnoreCase(title);
    }

    public Actor addActor(Actor actor) {
        return actorRepo.save(actor);
    }

    public Optional<Actor> deleteActorById(Long id) {
        Optional<Actor> actorById = actorRepo.findById(id);
        if (actorById.isEmpty()) {
            return Optional.empty();
        }
        Actor foundActor = actorById.get();
        actorRepo.deleteById(id);
        return Optional.of(foundActor);
    }



}
