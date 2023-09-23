package com.flickspick.exception.user;

import com.flickspick.exception.BusinessException;
import com.flickspick.exception.dto.ErrorType;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(ErrorType errorType) {
        super(errorType);
    }
}
