package com.flickspick.movie_recommendtype.infrastructure;

import com.flickspick.movie_recommendtype.domain.MovieRecommendType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRecommendTypeRepository extends JpaRepository<MovieRecommendType, Long> {
    Optional<MovieRecommendType> findByMovieId(Long movieId);
}
