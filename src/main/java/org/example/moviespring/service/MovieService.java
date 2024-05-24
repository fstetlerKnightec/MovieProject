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

    public ResponseEntity<Movie> getMovieByTitle(String title) {
        Optional<Movie> foundMovie = movieRepo.findByTitle(title);
        return foundMovie
            .map(movie -> ResponseEntity.status(HttpStatus.OK).body(movie))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public ResponseEntity<Movie> addMovie(Movie movie) {
        Optional<Movie> foundMovie = movieRepo.findByTitle(movie.getTitle());
        return foundMovie.map(m -> ResponseEntity.status(HttpStatus.CONFLICT).<Movie>build())
                .orElseGet(() -> {
                    movieRepo.save(movie);
                    return ResponseEntity.status(HttpStatus.CREATED).body(movie);
                });
    }

    public List<Movie> getMoviesByReleaseYear(int releaseYear) {
        return movieRepo.findByReleaseYearEquals(releaseYear);
    }

    public ResponseEntity<Movie> updateMovie(Movie movie, Long id) {
        if (movieRepo.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Movie foundMovie = movieRepo.findById(id).get();

        foundMovie.setTitle(movie.getTitle());
        foundMovie.setReleaseYear(movie.getReleaseYear());

        movieRepo.save(foundMovie);
        return ResponseEntity.status(HttpStatus.OK).body(foundMovie);
    }

    public ResponseEntity<Movie> deleteMovie(Long id) {
        if (movieRepo.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        movieRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
