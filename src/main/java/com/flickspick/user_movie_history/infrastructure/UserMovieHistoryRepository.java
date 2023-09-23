package com.flickspick.user_movie_history.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flickspick.user_movie_history.domain.UserMovieHistory;

public interface UserMovieHistoryRepository extends JpaRepository<UserMovieHistory, Long> {
	Optional<UserMovieHistory> findByRecommendTypeIdAndMovieId(Long recommendTypeId, Long movieId);

}
