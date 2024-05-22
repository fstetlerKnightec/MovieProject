package org.example.moviespring.service;

import org.example.moviespring.model.Movie;
import org.example.moviespring.repo.MovieRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<Movie> getMovieById(Long id) {
        Optional<Movie> foundMovie = movieRepo.findById(id);
        return foundMovie
                .map(movie -> ResponseEntity.status(HttpStatus.OK).body(movie))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public ResponseEntity<Movie> addMovie(Movie movie) {
        List<Movie> movies = movieRepo.findAll();
        if (movies.stream().anyMatch(m -> m.getTitle().equalsIgnoreCase(movie.getTitle()))) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        movieRepo.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movie);
    }

    public List<Movie> getMoviesByReleaseYear(int releaseYear) {
        return movieRepo.findByReleaseYearEquals(releaseYear);
    }

    public String updateMovie(Movie movie, Long id) {
        if (movieRepo.findById(id).isEmpty()) {
            return "Could not find movie with id " + id;
        }
        Movie foundMovie = movieRepo.findById(id).get();
        String movieNameOrig = foundMovie.getTitle();

        foundMovie.setTitle(movie.getTitle());
        foundMovie.setReleaseYear(movie.getReleaseYear());

        movieRepo.save(foundMovie);
        return "Successfully updated movie with name " + movieNameOrig;
    }

    public String deleteMovie(Long id) {
        if (movieRepo.findById(id).isEmpty()) {
            return "Could not find a movie with that ID";
        }
        String name = movieRepo.findById(id).get().getTitle();
        movieRepo.deleteById(id);
        return "Successfully deleted movie with name " + name;
    }
}
