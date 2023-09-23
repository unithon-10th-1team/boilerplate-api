package com.flickspick.character.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flickspick.movie.domain.Movie;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
}
