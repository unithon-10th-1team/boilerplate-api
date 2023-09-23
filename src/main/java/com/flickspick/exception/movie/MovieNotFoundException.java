package com.flickspick.exception.movie;

import com.flickspick.exception.BusinessException;
import com.flickspick.exception.dto.ErrorType;

public class MovieNotFoundException extends BusinessException {
	public MovieNotFoundException(ErrorType errorType) {
		super(errorType);
	}
}
