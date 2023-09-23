package com.flickspick.user.presentation;

import com.flickspick.auth.model.AuthUser;
import com.flickspick.common.model.dto.ResponseDto;
import com.flickspick.user.application.UserService;
import com.flickspick.user.dto.request.UserSignRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> sign(@RequestBody UserSignRequest request) {
        var response = userService.sign(request);
        return ResponseDto.created(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(AuthUser user, @PathVariable Long id) {
        var response = userService.get(user, id);
        return ResponseDto.ok(response);
    }
}
