package com.flickspick.exception.ott;

import com.flickspick.exception.BusinessException;
import com.flickspick.exception.dto.ErrorType;

public class OttNotFoundException extends BusinessException {

    public OttNotFoundException(ErrorType errorType) {
        super(errorType);
    }
}
