package com.flickspick.exception.movie_recommendtype;

import com.flickspick.exception.BusinessException;
import com.flickspick.exception.dto.ErrorType;

public class MovieRecommendTypeNotFoundException extends BusinessException {
    public MovieRecommendTypeNotFoundException(ErrorType errorType) {
        super(errorType);
    }
}
