package org.example.moviespring.controller;

import org.example.moviespring.DTOs.DTOConverter;
import org.example.moviespring.DTOs.MovieDTO;
import org.example.moviespring.model.Movie;
import org.example.moviespring.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<MovieDTO> getMovies() {
        return movieService.getMovies().stream().map(DTOConverter::convertToMovieDTO).toList();
    }

    @GetMapping("/getMovie/{id}")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable Long id) {
        Optional<Movie> movie = movieService.getMovieById(id);
        if (movie.isPresent()) {
            MovieDTO movieDTO = DTOConverter.convertToMovieDTO(movie.get());
            return ResponseEntity.ok(movieDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/getMovieByTitle/{title}")
    public ResponseEntity<MovieDTO> getMovieByTitle(@PathVariable String title) {
        Optional<Movie> foundMovie = movieService.getMovieByTitle(title);
        return foundMovie
            .map(movie -> ResponseEntity.status(HttpStatus.OK).body(DTOConverter.convertToMovieDTO(foundMovie.get())))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/getMoviesByReleaseYear/{releaseYear}")
    public List<MovieDTO> getMoviesByReleaseYear(@PathVariable int releaseYear) {
        return movieService.getMoviesByReleaseYear(releaseYear).stream().map(DTOConverter::convertToMovieDTO).toList();
    }

    @PostMapping("/addMovie")
    public ResponseEntity<MovieDTO> addMovie(@RequestBody Movie movie) {
        Optional<Movie> foundMovie = movieService.getMovieByTitle(movie.getTitle());
        return foundMovie.map(m -> ResponseEntity.status(HttpStatus.CONFLICT).<MovieDTO>build())
                .orElseGet(() -> {
                    movieService.addMovie(movie);
                    return ResponseEntity.status(HttpStatus.CREATED).body(DTOConverter.convertToMovieDTO(movie));
        });
    }

    @PutMapping("/updateMovie/{id}")
    public ResponseEntity<MovieDTO> updateMovie(@RequestBody Movie movie, @PathVariable Long id) {
        Optional<Movie> foundMovie = movieService.updateMovieById(movie, id);
        return foundMovie
                .map(m -> ResponseEntity.status(HttpStatus.OK).body(DTOConverter.convertToMovieDTO(m)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/deleteMovie/{id}")
    public ResponseEntity<MovieDTO> deleteMovie(@PathVariable Long id) {
        Optional<Movie> foundMovie = movieService.deleteMovieById(id);
        return foundMovie
                .map(m -> ResponseEntity.status(HttpStatus.OK).body(DTOConverter.convertToMovieDTO(m)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
