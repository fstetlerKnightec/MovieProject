package org.example.moviespring.repo;

import org.example.moviespring.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Long> {

    List<Movie> findByReleaseYearEquals(int year);

    Optional<Movie> findByTitleIgnoreCase(String title);

}
