package org.example.moviespring.controller;

import org.example.moviespring.model.Movie;
import org.example.moviespring.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Movie> getMovie(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    @GetMapping("/getMovieByTitle/{title}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String title) {
        return movieService.getMovieByTitle(title);
    }

    @GetMapping("/getMoviesByReleaseYear/{releaseYear}")
    public List<Movie> getMoviesByReleaseYear(@PathVariable int releaseYear) {
        return movieService.getMoviesByReleaseYear(releaseYear);
    }

    @PostMapping("/addMovie")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }

    @PutMapping("/updateMovie/{id}")
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie, @PathVariable Long id) {
        return movieService.updateMovie(movie, id);
    }

    @DeleteMapping("/deleteMovie/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable Long id) {
        return movieService.deleteMovie(id);
    }
}
