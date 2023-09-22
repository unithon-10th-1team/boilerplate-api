package com.flickspick.exception;

import com.flickspick.exception.dto.ErrorType;

public class AuthorizationException extends BusinessException{
    public AuthorizationException(ErrorType errorType) {
        super(errorType);
    }
}
