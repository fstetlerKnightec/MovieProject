package org.example.moviespring.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ACTORS")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "MOVIES_ACTORS",
               joinColumns = @JoinColumn(name = "ACTORS_ID", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "MOVIE_ID", referencedColumnName = "id"))
//    @JsonManagedReference
    private List<Movie> movies;

    public Actor() {
    }

    public Actor(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovie(List<Movie> movies) {
        this.movies = movies;
    }

}
