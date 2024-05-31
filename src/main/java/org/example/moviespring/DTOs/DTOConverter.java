package org.example.moviespring.DTOs;

import org.example.moviespring.model.Actor;
import org.example.moviespring.model.Movie;

import java.util.stream.Collectors;

public class DTOConverter {
    public static MovieDTO convertToMovieDTO(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setReleaseYear(movie.getReleaseYear());
        if (movie.getActors() != null) {
            movieDTO.setActors(movie.getActors().stream()
                    .map(DTOConverter::convertToSimpleActorDTO)
                    .collect(Collectors.toList()));
        }
        return movieDTO;
    }

    public static ActorDTO convertToActorDTO(Actor actor) {
        ActorDTO actorDTO = new ActorDTO();
        actorDTO.setId(actor.getId());
        actorDTO.setName(actor.getName());
        if (actor.getMovies() != null) {
            actorDTO.setMovies(actor.getMovies().stream()
                    .map(DTOConverter::convertToSimpleMovieDTO)
                    .collect(Collectors.toList()));
        }
        return actorDTO;
    }

    public static SimpleActorDTO convertToSimpleActorDTO(Actor actor) {
        SimpleActorDTO simpleActorDTO = new SimpleActorDTO();
        simpleActorDTO.setId(actor.getId());
        simpleActorDTO.setName(actor.getName());
        return simpleActorDTO;
    }

    public static SimpleMovieDTO convertToSimpleMovieDTO(Movie movie) {
        SimpleMovieDTO simpleMovieDTO = new SimpleMovieDTO();
        simpleMovieDTO.setId(movie.getId());
        simpleMovieDTO.setTitle(movie.getTitle());
        simpleMovieDTO.setReleaseYear(movie.getReleaseYear());
        return simpleMovieDTO;
    }
}
