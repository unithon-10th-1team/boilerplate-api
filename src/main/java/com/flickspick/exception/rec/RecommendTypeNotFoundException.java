package com.flickspick.exception.rec;

import com.flickspick.exception.BusinessException;
import com.flickspick.exception.dto.ErrorType;

public class RecommendTypeNotFoundException extends BusinessException {
	public RecommendTypeNotFoundException(ErrorType errorType) {
		super(errorType);
	}
}
