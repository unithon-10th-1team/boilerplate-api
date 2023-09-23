package com.flickspick.recommendtype.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flickspick.recommendtype.domain.RecommendType;

@Repository
public interface RecommendTypeRepository extends JpaRepository<RecommendType, Long> {
	Optional<RecommendType> findByRecommendType(String recommendType);
}
