package com.flickspick.user.dto.response;

import com.flickspick.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private String nickname;

    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getNickname());
    }
}
