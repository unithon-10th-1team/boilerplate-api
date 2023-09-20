package com.goofy.boilerplate.exception.dto;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ErrorDto implements Serializable {
    private final String message;
    private final String reason;

    public ErrorDto(String message, String reason) {
        this.message = message;
        this.reason = reason;
    }

    public ErrorDto(ErrorType message) {
        this.message = message.name();
        this.reason = message.getMessage();
    }
}
