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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

        Assertions.assertEquals(savedMovie.getID(), 1L);
        Assertions.assertEquals(savedMovie.getTITLE(), "Golden Eye");
        Assertions.assertEquals(savedMovie.getRELEASEYEAR(), 1999);
    }

    @Test
    public void MovieService_GetMovies_ReturnsMovies() {
        Movie movie1 = new Movie(1L, "Golden Eye", 1999);
        Movie movie2 = new Movie(2L, "A New Hope", 1977);
        List<Movie> movies = new ArrayList<>(Arrays.asList(movie1, movie2));

        when(movieRepo.findAll()).thenReturn(movies);
        List<Movie> movieList = movieService.getMovies();

        Assertions.assertNotNull(movieList);
        Assertions.assertEquals(movieList.get(0).getTITLE(), "Golden Eye");
    }

    @Test
    public void MovieService_GetMovieById_ReturnMovie() {
        Movie movie = new Movie(1L, "Golden Eye", 1999);

        when(movieRepo.findById(1L)).thenReturn(Optional.of(movie));
        Optional<Movie> foundMovie = movieService.getMovieById(1L);

        Assertions.assertEquals(foundMovie.get().getTITLE(), "Golden Eye");
    }

    @Test
    public void MovieService_GetMovieByReleaseYear_ReturnMoviesFromYear() {
        Movie movie1 = new Movie(1L, "Golden Eye", 1999);
        Movie movie2 = new Movie(2L, "La La Land", 2016);
        Movie movie3 = new Movie(3L, "AnotherMovie", 2016);

        when(movieRepo.findAll()).thenReturn(new ArrayList<>(Arrays.asList(movie1, movie2, movie3)));

        List<Movie> moviesFrom2016 = movieService.getMoviesByReleaseYear(2016);
        Assertions.assertEquals(moviesFrom2016.size(), 2);
        Assertions.assertEquals(moviesFrom2016.get(0).getTITLE(), "La La Land");
        Assertions.assertEquals(moviesFrom2016.get(1).getTITLE(), "AnotherMovie");
    }

    @Test
    public void MovieService_UpdateMovie_MovieIsUpdated() {
        Movie movie1 = new Movie(1L, "Golden Eye", 1999);
        Movie movie2 = new Movie(2L, "A New Hope", 1977);

        when(movieRepo.findById(1L)).thenReturn(Optional.of(movie1));
        when(movieRepo.save(Mockito.any(Movie.class))).thenReturn(movie1);
        String updatedMovie = movieService.updateMovie(movie2, 1L);

        Assertions.assertEquals(updatedMovie, "Successfully updated movie with name A New Hope");
    }

    @Test
    public void MovieService_UpdatedNonExistentMovie_ReturnNotFoundMessage() {
        Movie movie2 = new Movie(2L, "A New Hope", 1977);

        when(movieRepo.findById(3L)).thenReturn(Optional.empty());
        String updatedMovie = movieService.updateMovie(movie2, 3L);

        Assertions.assertEquals(updatedMovie, "Could not find movie with id 3");
    }


    @Test
    public void MovieService_DeleteMovieById_ReturnVoid() {
        Movie movie1 = new Movie(1L, "Golden Eye", 1999);

        when(movieRepo.findById(1L)).thenReturn(Optional.of(movie1));
        String movieString = movieService.deleteMovie(1L);

        Assertions.assertEquals(movieString, "Successfully deleted movie with name Golden Eye");
    }

    @Test
    public void MovieService_DeleteMovieByNonExistentId_ReturnNotFoundString() {
        when(movieRepo.findById(1L)).thenReturn(Optional.empty());
        String movieString = movieService.deleteMovie(1L);

        Assertions.assertEquals(movieString, "Could not find a movie with that ID");
    }
}
