package com.flickspick.auth.application;

import com.flickspick.auth.dto.request.AuthLoginRequest;
import com.flickspick.auth.dto.response.AuthLoginResponse;
import com.flickspick.exception.AuthLoginException;
import com.flickspick.exception.dto.ErrorType;
import com.flickspick.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public AuthLoginResponse login(AuthLoginRequest request) {
        var user =
                userRepository
                        .findByUsernameAndPassword(request.getUsername(), request.getPassword())
                        .orElseThrow(() -> new AuthLoginException(ErrorType.FAIL_TO_LOGIN_ERROR));

        var token = tokenService.jwtBuilder(user.getId(), user.getNickname());

        return new AuthLoginResponse(user.getNickname(), token);
    }
}
