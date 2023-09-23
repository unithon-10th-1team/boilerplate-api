package com.flickspick.auth.presentation;

import com.flickspick.auth.application.AuthService;
import com.flickspick.auth.dto.request.AuthLoginRequest;
import com.flickspick.auth.model.AuthUser;
import com.flickspick.common.model.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "인증,인가 관리")
@RestController
@RequestMapping(path = "/api/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthLoginRequest request) {
        var response = authService.login(request);
        return ResponseDto.ok(response);
    }

    @GetMapping("/test")
    public ResponseEntity<?> test(AuthUser user) {
        return ResponseEntity.ok(user);
    }
}
