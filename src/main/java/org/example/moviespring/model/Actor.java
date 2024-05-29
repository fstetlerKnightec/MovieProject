package org.example.moviespring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ACTORS")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    String name;

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
}
