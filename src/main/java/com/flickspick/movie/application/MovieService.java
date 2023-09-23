package com.flickspick.movie.application;

import com.flickspick.exception.dto.ErrorType;
import com.flickspick.exception.movie.MovieNotFoundException;
import com.flickspick.exception.rec.RecommendTypeNotFoundException;
import com.flickspick.movie.domain.Movie;
import com.flickspick.movie.dto.MovieResponse;
import com.flickspick.movie.infrastructure.MovieRepository;
import com.flickspick.movie.model.MovieModel;
import com.flickspick.recommendtype.model.RecTypeModel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private Map<Long, MovieModel> movieModels;

    @Scheduled(fixedRate = 1000 * 60 * 5, initialDelayString = "0")
    public void refreshMovieModels() {
        log.info("refresh movieModels info start");
        movieModels = refresh();
        log.info("refresh movieModels info complete");
    }

    public Map<Long, MovieModel> refresh() {
        return movieRepository.findAll()
                .stream()
                .map(MovieModel::toModel)
                .collect(Collectors.toMap(MovieModel::getId, Function.identity()));
    }

    public MovieModel get(Long id) {
        var movieModel = movieModels.get(id);

        if (movieModel == null) {
            throw new MovieNotFoundException(ErrorType.MOVIE_NOT_FOUND_ERROR);
        }

        return movieModel;
    }

    public List<MovieModel> getList(Long movieId, int count) {
        List<MovieModel> movieList = movieModels.keySet()
                .stream().map(movieModels::get)
                .filter(movieModel -> movieModel.getId() != movieId)
                .collect(Collectors.toList());

        Collections.shuffle(movieList);

        int limit = Math.min(movieList.size(), count);

        return movieList.subList(0, limit);
    }

    public Long getMovieCount() {
        return movieRepository.count();
    }

    public MovieResponse getMovie(Long movieId) {
        var movieModel = get(movieId);
        var recMovies = getList(movieId, 3);
        return MovieResponse.toResponse(movieModel, recMovies);
    }
}
