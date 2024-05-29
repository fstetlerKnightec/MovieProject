package org.example.moviespring.repo;

import org.example.moviespring.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActorRepo extends JpaRepository<Actor, Long> {

    Optional<Actor> findByNameIgnoreCase(String name);

}
