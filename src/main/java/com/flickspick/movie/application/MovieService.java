package com.flickspick.movie.application;

import com.flickspick.exception.dto.ErrorType;
import com.flickspick.exception.movie.MovieNotFoundException;
import com.flickspick.movie.domain.Movie;
import com.flickspick.movie.infrastructure.MovieRepository;
import com.flickspick.movie.model.MovieModel;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieModel getMovieModel(Long movieId) {
        var movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException(ErrorType.MOVIE_NOT_FOUND_ERROR));
        return MovieModel.toModel(movie);
    }

    @Async(value = "taskExecutor")
    public CompletableFuture<MovieModel> asyncGetMovieModel(Long movieId) {
        return CompletableFuture.completedFuture(getMovieModel(movieId));
    }

    public List<MovieModel> getMovieModelList(Long movieId, int count) {
        List<Movie> movieList = movieRepository.findAll()
                .stream().filter(movie -> movie.getId() != movieId)
                .collect(Collectors.toList());

        Collections.shuffle(movieList);

        int limit = Math.min(movieList.size(), count);

        return movieList.subList(0, limit)
                .stream()
                .map(MovieModel::toModel)
                .collect(Collectors.toList());
    }
    
    @Async(value = "taskExecutor")
    public CompletableFuture<List<MovieModel>> asyncGetMovieModelList(Long movieId, int count) {
        return CompletableFuture.completedFuture(getMovieModelList(movieId, count));
    }

    public Long getMovieCount() {
        return movieRepository.count();
    }
}
