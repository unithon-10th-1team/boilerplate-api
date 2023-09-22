package com.flickspick.exception.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorType {
    NOT_EXIST_USER(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다."),
    EXPIRED_TOKEN(HttpStatus.BAD_REQUEST, "해당 토큰은 만료된 토큰입니다."),
    CONFLICT_ERROR(HttpStatus.BAD_REQUEST, "예기치 못한 에러가 발생했습니다."),

    DUPLICATION_USERNAME_ERROR(HttpStatus.BAD_REQUEST, "중복된 Id 입니다."),
    DUPLICATION_NICKNAME_ERROR(HttpStatus.BAD_REQUEST, "중복된 닉네임 입니다."),
    ;

    private final HttpStatus status;
    private final String message;
}
