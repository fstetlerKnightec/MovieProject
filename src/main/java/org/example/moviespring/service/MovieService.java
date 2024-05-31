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

    public Optional<Movie> getMovieByTitle(String title) {
        return movieRepo.findByTitleIgnoreCase(title);
    }

    public Movie addMovie(Movie movie) {
        return movieRepo.save(movie);
    }

    public List<Movie> getMoviesByReleaseYear(int releaseYear) {
        return movieRepo.findByReleaseYearEquals(releaseYear);
    }

    public Optional<Movie> updateMovieById(Movie movie, Long id) {
        Optional<Movie> movieById = movieRepo.findById(id);



        if (movieById.isEmpty()) {
            return Optional.empty();
        }
        Movie foundMovie = movieById.get();

        foundMovie.setTitle(movie.getTitle());
        foundMovie.setReleaseYear(movie.getReleaseYear());
        foundMovie.setActors(movie.getActors());


        movieRepo.save(foundMovie);
        return Optional.of(foundMovie);
    }

    public Optional<Movie> deleteMovieById(Long id) {
        Optional<Movie> movieById = movieRepo.findById(id);
        if (movieById.isEmpty()) {
            return Optional.empty();
        }
        Movie foundMovie = movieById.get();
        movieRepo.deleteById(id);
        return Optional.of(foundMovie);
    }
}
