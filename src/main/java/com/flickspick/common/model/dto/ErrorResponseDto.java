package com.flickspick.common.model.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponseDto implements Serializable {
    private final String errorCode;
    private final String reason;
}
