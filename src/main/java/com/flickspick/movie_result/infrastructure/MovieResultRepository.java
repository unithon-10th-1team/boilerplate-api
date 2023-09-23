package com.flickspick.movie_result.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flickspick.movie_result.domain.MovieResult;

public interface MovieResultRepository extends JpaRepository<MovieResult, Long> {
	Optional<MovieResult> findByRecommendTypeIdAndMovieId(Long recommendTypeId, Long movieId);
}
