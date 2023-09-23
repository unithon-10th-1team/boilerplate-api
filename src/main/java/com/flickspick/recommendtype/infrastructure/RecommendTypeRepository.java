package com.flickspick.recommendtype.infrastructure;

import com.flickspick.recommendtype.domain.RecommendType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendTypeRepository extends JpaRepository<RecommendType, Long> {
    Optional<RecommendType> findByRecommendType(String recommendType);
}
