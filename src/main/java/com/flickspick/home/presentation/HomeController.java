package com.flickspick.home.presentation;

import com.flickspick.auth.model.AuthUser;
import com.flickspick.common.model.dto.ResponseDto;
import com.flickspick.home.application.HomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "홈")
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class HomeController {
    private final HomeService homeService;

    @Operation(summary = "홈페이지")
    @GetMapping(path = "/api/v1/home")
    public ResponseEntity<?> getHome(AuthUser user) {
        var response = homeService.getHome(user);
        return ResponseDto.ok(response);
    }

    @Operation(summary = "[mock] 마이페이지")
    @GetMapping(path = "/api/v1/me")
    public ResponseEntity<?> getMyProfile(AuthUser user) {
        var response = homeService.getMyProfile(user);
        return ResponseDto.ok(response);
    }
}
