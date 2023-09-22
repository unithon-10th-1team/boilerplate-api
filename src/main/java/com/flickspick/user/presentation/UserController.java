package com.flickspick.user.presentation;

import com.flickspick.common.model.dto.ResponseDto;
import com.flickspick.user.application.UserService;
import com.flickspick.user.dto.request.UserSignRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> sign(@RequestBody UserSignRequest request) {
        var response = userService.sign(request);
        return ResponseDto.created(response);
    }
}
