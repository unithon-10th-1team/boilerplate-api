package com.flickspick.movie_result.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flickspick.movie_result.domain.MovieResult;

public interface MovieResultRepository extends JpaRepository<MovieResult, Long> {}
