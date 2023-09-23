package com.flickspick.movie.application;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.flickspick.exception.dto.ErrorType;
import com.flickspick.exception.movie.MovieNotFoundException;
import com.flickspick.movie.domain.Movie;
import com.flickspick.movie.infrastructure.MovieRepository;
import com.flickspick.movie.model.MovieModel;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {
	private final MovieRepository movieRepository;

	public MovieModel getMovieModel(Long movieId) {
		Movie movie = movieRepository.findById(movieId)
				.orElseThrow(() -> new MovieNotFoundException(ErrorType.MOVIE_NOT_FOUND_ERROR));
		return MovieModel.toModel(movie);
	}

	public List<MovieModel> getMovieModelList(Long movieId, int count) {
		List<Movie> movieList = movieRepository.findAll()
				.stream().filter(movie -> movie.getId() != movieId)
				.collect(Collectors.toList());
		Collections.shuffle(movieList);

		int limit = Math.min(movieList.size(), count);
		return movieList.subList(0, limit).stream().map(
				movie -> MovieModel.toModel(movie)
		).collect(Collectors.toList());
	}

	public Long getMovieCount() {
		return movieRepository.count();
	}
}
