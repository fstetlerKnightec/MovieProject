package org.example.moviespring.DTOs;

import java.util.List;

public class MovieDTO {

    private Long id;
    private String title;
    private int releaseYear;
    private List<SimpleActorDTO> actors;

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SimpleActorDTO> getActors() {
        return actors;
    }

    public void setActors(List<SimpleActorDTO> actors) {
        this.actors = actors;
    }
}
