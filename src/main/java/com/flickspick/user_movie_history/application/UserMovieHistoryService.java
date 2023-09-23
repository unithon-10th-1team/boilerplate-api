package com.flickspick.user_movie_history.application;

import com.flickspick.exception.dto.ErrorType;
import com.flickspick.exception.user_movie_history.UserMovieHistoryNotFoundException;
import com.flickspick.user_movie_history.domain.UserMovieHistory;
import com.flickspick.user_movie_history.infrastructure.UserMovieHistoryRepository;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserMovieHistoryService {
    private final UserMovieHistoryRepository userMovieHistoryRepository;

    @Transactional
    public UserMovieHistory saveUserMovieHistory(UserMovieHistory userMovieHistory) {
        return userMovieHistoryRepository.save(userMovieHistory);
    }

    public UserMovieHistory getRecentHistory(Long userId) {
        var userMovieHistory =
                userMovieHistoryRepository.findTopByUserIdOrderByCreatedAtDesc(userId);

        if (userMovieHistory.isEmpty()) {
            return userMovieHistoryRepository
                    .findTop1ByOrderByCreatedAtDesc()
                    .orElseThrow(
                            () ->
                                    new UserMovieHistoryNotFoundException(
                                            ErrorType.USER_MOVIE_HISTORY_NOT_FOUND));
        }

        return userMovieHistory.get();
    }

    @Async
    public CompletableFuture<UserMovieHistory> asyncGetRecentHistory(Long userId) {
        return CompletableFuture.completedFuture(getRecentHistory(userId));
    }
}
