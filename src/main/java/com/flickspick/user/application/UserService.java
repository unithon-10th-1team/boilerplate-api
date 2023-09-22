package com.flickspick.user.application;

import com.flickspick.auth.application.TokenService;
import com.flickspick.exception.dto.ErrorType;
import com.flickspick.exception.user.UserSignInvalidException;
import com.flickspick.user.domain.User;
import com.flickspick.user.dto.request.UserSignRequest;
import com.flickspick.user.dto.response.UserSignResponse;
import com.flickspick.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Transactional
    public UserSignResponse sign(UserSignRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserSignInvalidException(ErrorType.DUPLICATION_USERNAME_ERROR);
        }

        if (userRepository.existsByNickname(request.getNickname())) {
            throw new UserSignInvalidException(ErrorType.DUPLICATION_NICKNAME_ERROR);
        }

        var savedUser = userRepository.save(
                User.builder()
                        .username(request.getUsername())
                        .nickname(request.getNickname())
                        .password(request.getPassword())
                        .build()
        );

        var token = tokenService.jwtBuilder(savedUser.getId(), savedUser.getNickname());


        return new UserSignResponse(savedUser.getNickname(), token);
    }
}
