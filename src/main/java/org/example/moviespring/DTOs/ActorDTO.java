package org.example.moviespring.DTOs;

import java.util.List;

public class ActorDTO {

    private Long id;
    private String name;
    private List<SimpleMovieDTO> movies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SimpleMovieDTO> getMovies() {
        return movies;
    }

    public void setMovies(List<SimpleMovieDTO> movies) {
        this.movies = movies;
    }
}
