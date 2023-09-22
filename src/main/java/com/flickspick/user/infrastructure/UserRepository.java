package com.flickspick.user.infrastructure;

import com.flickspick.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Transactional
    Boolean existsByUsername(String username);

    @Transactional
    Boolean existsByNickname(String nickname);
}
