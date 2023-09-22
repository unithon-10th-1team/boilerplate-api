package com.flickspick.exception;

import com.flickspick.exception.dto.ErrorType;

public class AuthLoginException extends BusinessException {
    public AuthLoginException(ErrorType errorType) {
        super(errorType);
    }
}
