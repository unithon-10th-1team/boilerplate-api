package com.flickspick.user_movie_history.application;

import org.springframework.stereotype.Service;

import com.flickspick.rec.dto.RecResponse;
import com.flickspick.user_movie_history.domain.UserMovieHistory;
import com.flickspick.user_movie_history.infrastructure.UserMovieHistoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserMovieHistoryService {
	private final UserMovieHistoryRepository userMovieHistoryRepository;

	public UserMovieHistory saveUserMovieHistory(UserMovieHistory userMovieHistory) {
		return userMovieHistoryRepository.save(userMovieHistory);
	}
}
