package com.flickspick.ott.infrastructure;

import com.flickspick.ott.domain.Ott;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OttRepository extends JpaRepository<Ott, Long> {}
