package org.example.repo;

import org.assertj.core.api.Assertions;
import org.example.moviespring.model.Movie;
import org.example.moviespring.repo.MovieRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class MovieRepositoryTest {

    @Autowired
    private MovieRepo movieRepo;

    @Test
    public void MovieRepo_SaveAll_ReturnSavedMovies() {

        Movie movie = new Movie(1L, "Golden Eye", 1999);

        Movie savedMovie = movieRepo.save(movie);

        Assertions.assertThat(savedMovie).isNotNull();
        Assertions.assertThat(savedMovie.getID()).isEqualTo(1);

    }
}
