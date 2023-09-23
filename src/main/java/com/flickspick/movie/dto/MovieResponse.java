package com.flickspick.movie.dto;

import java.util.List;

import com.flickspick.movie.model.MovieModel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieResponse {
	private MovieModel movie;
	private List<MovieModel> recMovies;

	public static MovieResponse toResponse(MovieModel movieModel, List<MovieModel> recMovies) {
		return new MovieResponse(movieModel, recMovies);
	}
}
