package com.flickspick.user.presentation;

import com.flickspick.auth.model.AuthUser;
import com.flickspick.common.model.dto.ResponseDto;
import com.flickspick.user.application.UserService;
import com.flickspick.user.dto.request.UserSignRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "사용자 관리")
@RestController
@RequestMapping(path = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "회원가입")
    @PostMapping
    public ResponseEntity<?> sign(@RequestBody UserSignRequest request) {
        var response = userService.sign(request);
        return ResponseDto.created(response);
    }

    @Operation(summary = "유저 정보 조회")
    @GetMapping("/{id}")
    public ResponseEntity<?> get(AuthUser user, @PathVariable Long id) {
        var response = userService.get(user, id);
        return ResponseDto.ok(response);
    }
}
