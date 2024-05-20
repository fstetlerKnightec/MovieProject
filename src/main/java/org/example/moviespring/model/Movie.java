package org.example.moviespring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private String TITLE;

    private int RELEASEYEAR;

    public Movie() {
    }

    public Movie(Long id, String title, int release_year) {
        this.ID = id;
        this.TITLE = title;
        this.RELEASEYEAR = release_year;
    }

    public Long getID() {
        return ID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public int getRELEASEYEAR() {
        return RELEASEYEAR;
    }

    public void setRELEASEYEAR(int RELEASEYEAR) {
        this.RELEASEYEAR = RELEASEYEAR;
    }
}
