package com.flickspick.user_movie_history.infrastructure;

import com.flickspick.user_movie_history.domain.UserMovieHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserMovieHistoryRepository extends JpaRepository<UserMovieHistory, Long> {
    Optional<UserMovieHistory> findByRecommendTypeIdAndMovieId(Long recommendTypeId, Long movieId);

    Optional<UserMovieHistory> findTopByUserIdOrderByCreatedAtDesc(Long userId);

    Optional<UserMovieHistory> findTop1ByOrderByCreatedAtDesc();
}
