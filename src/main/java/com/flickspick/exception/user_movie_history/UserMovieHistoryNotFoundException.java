package com.flickspick.exception.user_movie_history;

import com.flickspick.exception.BusinessException;
import com.flickspick.exception.dto.ErrorType;

public class UserMovieHistoryNotFoundException extends BusinessException {
    public UserMovieHistoryNotFoundException(ErrorType errorType) {
        super(errorType);
    }
}
