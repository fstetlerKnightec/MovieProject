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
        Movie savedMovie = movieService.addMovie(movie);

        Assertions.assertEquals(Objects.requireNonNull(savedMovie).getId(), 1L);
        Assertions.assertEquals(savedMovie.getTitle(), "Golden Eye");
        Assertions.assertEquals(savedMovie.getReleaseYear(), 1999);
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
        Assertions.assertEquals(movieList.get(1).getTitle(), "A New Hope");
    }

    @Test
    public void MovieService_GetMovieById_ReturnMovie() {
        Movie movie = new Movie(1L, "Golden Eye", 1999);

        when(movieRepo.findById(1L)).thenReturn(Optional.of(movie));
        Movie foundMovie = movieService.getMovieById(1L).get();

        Assertions.assertEquals(foundMovie.getTitle(), "Golden Eye");
    }

    @Test
    public void MovieService_GetMovieByNonExistentId_ReturnEmpty() {
        when(movieRepo.findById(1L)).thenReturn(Optional.empty());
        Optional<Movie> foundMovieCode = movieService.getMovieById(1L);

        Assertions.assertEquals(foundMovieCode, Optional.empty());
    }

    @Test
    public void MovieService_UpdateMovie_MovieIsUpdated() {
        Movie movie1 = new Movie(1L, "Golden Eye", 1999);
        Movie movie2 = new Movie(2L, "A New Hope", 1977);

        when(movieRepo.findById(1L)).thenReturn(Optional.of(movie1));
        when(movieRepo.save(Mockito.any(Movie.class))).thenReturn(movie1);
        Optional<Movie> updatedMovie = movieService.updateMovieById(movie2, 1L);

        Assertions.assertEquals(movie1, updatedMovie.get());
    }

    @Test
    public void MovieService_UpdatedNonExistentMovie_ReturnNotFoundMessage() {
        Movie movie2 = new Movie(2L, "A New Hope", 1977);

        when(movieRepo.findById(3L)).thenReturn(Optional.empty());
        Optional<Movie> updatedMovie = movieService.updateMovieById(movie2, 3L);

        Assertions.assertEquals(Optional.empty(), updatedMovie);
    }

    @Test
    public void MovieService_DeleteMovieById_ReturnSuccess() {
        Movie movie1 = new Movie(1L, "Golden Eye", 1999);

        when(movieRepo.findById(1L)).thenReturn(Optional.of(movie1));
        Optional<Movie> deletedMovie = movieService.deleteMovieById(1L);

        Assertions.assertEquals(movie1, deletedMovie.get());
    }

    @Test
    public void MovieService_DeleteMovieByNonExistentId_ReturnNotFoundString() {
        when(movieRepo.findById(1L)).thenReturn(Optional.empty());
        Optional<Movie> deletedMovie = movieService.deleteMovieById(1L);

        Assertions.assertEquals(Optional.empty(), deletedMovie);
    }
}
