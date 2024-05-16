package org.example.moviespring.service;

import org.example.moviespring.model.Movie;
import org.example.moviespring.repo.MovieRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepo movieRepo;

    public MovieService(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    public List<Movie> getMovies() {
        return movieRepo.findAll();
    }

    public Optional<Movie> getMovieById(Long id) {
        return movieRepo.findById(id);
    }

    public Movie addMovie(Movie movie) {
        return movieRepo.save(movie);
    }

    public List<Movie> getMoviesByReleaseYear(int releaseYear) {
        return movieRepo.findAll().stream().filter(m -> m.getRELEASEYEAR() == releaseYear).toList();
    }

    public Movie updateMovie(Movie movie) {
        Optional<Movie> newMovie = getMovieById(movie.getID());
        if (newMovie.isEmpty()) {
            throw new NullPointerException("Could not find object with that ID");
        }
        newMovie.get().setTITLE(movie.getTITLE());
        newMovie.get().setRELEASEYEAR(movie.getRELEASEYEAR());
        return movieRepo.save(newMovie.get());
    }

    public void deleteMovie(Long id) {
        movieRepo.deleteById(id);
        System.out.println("Successfully deleted movie with ID " + id);
    }
}
