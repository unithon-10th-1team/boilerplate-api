package com.flickspick.movie_recommendtype.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flickspick.movie_recommendtype.domain.MovieRecommendType;

public interface MovieRecommendTypeRepository extends JpaRepository<MovieRecommendType, Long> {
}
