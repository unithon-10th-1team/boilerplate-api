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
    FAIL_TO_LOGIN_ERROR(HttpStatus.UNAUTHORIZED, "로그인을 실패했습니다."),
    AUTHORIZATION_ERROR(HttpStatus.UNAUTHORIZED, "인증, 인가 오류"),
    USER_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "유저 정보를 찾을 수 없습니다."),

    // rec
    RECOMMEND_TYPE_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "추천 타입을 찾을 수 없습니다."),

    // movie
    MOVIE_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "영화 정보를 찾을 수 없습니다."),

    // movieRecommendType
    MOVIE_RECOMMEND_TYPE_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "영화 타입 매핑 정보를 찾을 수 없습니다."),

    // userMovieHistory
    USER_MOVIE_HISTORY_NOT_FOUND(HttpStatus.NOT_FOUND, "유저 영화 히스토리를 찾을 수 없습니다."),

    // ott
    OTT_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "OTT 정보를 찾을 수 없습니다."),
    ;

    private final HttpStatus status;
    private final String message;
}
