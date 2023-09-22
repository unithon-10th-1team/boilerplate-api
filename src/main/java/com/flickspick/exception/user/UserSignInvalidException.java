package com.flickspick.exception.user;

import com.flickspick.exception.BusinessException;
import com.flickspick.exception.dto.ErrorType;

public class UserSignInvalidException extends BusinessException {
    public UserSignInvalidException(ErrorType errorType) {
        super(errorType);
    }
}
