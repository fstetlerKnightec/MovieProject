package org.example.service;

import org.example.moviespring.model.Movie;
import org.example.moviespring.repo.MovieRepo;
import org.example.moviespring.service.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieRepo movieRepo;

    @InjectMocks
    private MovieService movieService;

    @Test
    public void MovieService_PostMovieToRepo_ReturnsMovie() {
        Movie movie = new Movie(1L, "Golden Eye", 1999);

        when(movieRepo.save(Mockito.any(Movie.class))).thenReturn(movie);
        ResponseEntity<Movie> savedMovie = movieService.addMovie(movie);

        Assertions.assertEquals(Objects.requireNonNull(savedMovie.getBody()).getId(), 1L);
        Assertions.assertEquals(savedMovie.getBody().getTitle(), "Golden Eye");
        Assertions.assertEquals(savedMovie.getBody().getReleaseYear(), 1999);
    }

    @Test
    public void MovieService_PostMovieToRepoAlreadyExists_ReturnsHttpStatusConflict() {
        Movie movie1 = new Movie(1L, "Golden Eye", 1999);

        when(movieRepo.findByTitle("Golden Eye")).thenReturn(Optional.of(movie1));
        ResponseEntity<Movie> addMovie = movieService.addMovie(movie1);

        Assertions.assertEquals(addMovie.getStatusCode(), HttpStatus.CONFLICT);
    }

    @Test
    public void MovieService_GetMovies_ReturnsMovies() {
        Movie movie1 = new Movie(1L, "Golden Eye", 1999);
        Movie movie2 = new Movie(2L, "A New Hope", 1977);
        List<Movie> movies = new ArrayList<>(Arrays.asList(movie1, movie2));

        when(movieRepo.findAll()).thenReturn(movies);
        List<Movie> movieList = movieService.getMovies();

        Assertions.assertNotNull(movieList);
        Assertions.assertEquals(movieList.get(0).getTitle(), "Golden Eye");
    }

    @Test
    public void MovieService_GetMovieById_ReturnMovie() {
        Movie movie = new Movie(1L, "Golden Eye", 1999);

        when(movieRepo.findById(1L)).thenReturn(Optional.of(movie));
        Movie foundMovie = movieService.getMovieById(1L).getBody();

        Assertions.assertEquals(foundMovie.getTitle(), "Golden Eye");
    }

    @Test
    public void MovieService_GetMovieByNonExistentId_ReturnEmpty() {
        when(movieRepo.findById(1L)).thenReturn(Optional.empty());
        HttpStatusCode foundMovieCode = movieService.getMovieById(1L).getStatusCode();

        Assertions.assertEquals(foundMovieCode, HttpStatus.NOT_FOUND);
    }

    @Test
    public void MovieService_UpdateMovie_MovieIsUpdated() {
        Movie movie1 = new Movie(1L, "Golden Eye", 1999);
        Movie movie2 = new Movie(2L, "A New Hope", 1977);

        when(movieRepo.findById(1L)).thenReturn(Optional.of(movie1));
        when(movieRepo.save(Mockito.any(Movie.class))).thenReturn(movie1);
        ResponseEntity<Movie> updatedMovie = movieService.updateMovie(movie2, 1L);

        Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).body(movie1), updatedMovie);
    }

    @Test
    public void MovieService_UpdatedNonExistentMovie_ReturnNotFoundMessage() {
        Movie movie2 = new Movie(2L, "A New Hope", 1977);

        when(movieRepo.findById(3L)).thenReturn(Optional.empty());
        ResponseEntity<Movie> updatedMovie = movieService.updateMovie(movie2, 3L);

        Assertions.assertEquals(ResponseEntity.status(HttpStatus.NOT_FOUND).build(), updatedMovie);
    }

    @Test
    public void MovieService_DeleteMovieById_ReturnSuccess() {
        Movie movie1 = new Movie(1L, "Golden Eye", 1999);

        when(movieRepo.findById(1L)).thenReturn(Optional.of(movie1));
        ResponseEntity<Movie> deletedMovie = movieService.deleteMovie(1L);

        Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).build(), deletedMovie);
    }

    @Test
    public void MovieService_DeleteMovieByNonExistentId_ReturnNotFoundString() {
        when(movieRepo.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Movie> deletedMovie = movieService.deleteMovie(1L);

        Assertions.assertEquals(ResponseEntity.status(HttpStatus.NOT_FOUND).build(), deletedMovie);
    }
}
