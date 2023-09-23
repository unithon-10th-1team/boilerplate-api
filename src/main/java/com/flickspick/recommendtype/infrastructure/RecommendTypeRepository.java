package com.flickspick.recommendtype.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flickspick.recommendtype.domain.RecommendType;

@Repository
public interface RecommendTypeRepository extends JpaRepository<RecommendType, Long> {
}
