package org.example.moviespring.controller;

import org.example.moviespring.model.Movie;
import org.example.moviespring.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/getMovies")
    public List<Movie> getMovies() {
        return movieService.getMovies();
    }

    @GetMapping("/getMovie/{id}")
    public Optional<Movie> getMovie(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    @GetMapping("/getMoviesByReleaseYear/{releaseYear}")
    public List<Movie> getMoviesByReleaseYear(@PathVariable int releaseYear) {
        return movieService.getMoviesByReleaseYear(releaseYear);
    }

    @PostMapping("/addMovie")
    public Movie addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }

    @PutMapping("/updateMovie")
    public Movie updateMovie(@RequestBody Movie movie) {
        return movieService.updateMovie(movie);
    }

    @DeleteMapping("/deleteMovie/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }
}
