package com.flickspick.movie.presentation;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flickspick.auth.model.AuthUser;
import com.flickspick.common.model.dto.ResponseDto;
import com.flickspick.home.application.HomeService;
import com.flickspick.movie.application.MovieService;
import com.flickspick.movie.dto.MovieResponse;
import com.flickspick.movie.model.MovieModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "무비")
@RestController
@RequestMapping(value = "/api/v1/movies", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @Operation(summary = "무비 조회")
    @GetMapping(path = "/{movieId}")
    public ResponseEntity<?> getMovie(AuthUser user, @PathVariable Long movieId) {
        MovieResponse response = movieService.getMovie(movieId);
        return ResponseDto.ok(response);
    }
}
