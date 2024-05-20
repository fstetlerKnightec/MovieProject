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

    public String updateMovie(Movie movie, Long id) {
        if (movieRepo.findById(id).isEmpty()) {
            return "Could not find movie with id " + id;
        }
        Movie foundMovie = movieRepo.findById(id).get();

        foundMovie.setTITLE(movie.getTITLE());
        foundMovie.setRELEASEYEAR(movie.getRELEASEYEAR());

        movieRepo.save(foundMovie);
        return "Successfully updated movie with name " + foundMovie.getTITLE();
    }

    public String deleteMovie(Long id) {
        if (movieRepo.findById(id).isEmpty()) {
            return "Could not find a movie with that ID";
        }
        String name = movieRepo.findById(id).get().getTITLE();
        movieRepo.deleteById(id);
        return "Successfully deleted movie with name " + name;
    }
}