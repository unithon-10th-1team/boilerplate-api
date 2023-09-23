package com.flickspick.movie_character.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flickspick.movie_character.domain.MovieCharacter;

public interface MovieCharacterRepository extends JpaRepository<MovieCharacter, Long> {}
