package com.flickspick.user.model;

import com.flickspick.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserModel {
    private Long id;
    private String username;
    private String nickname;

    public static UserModel from(User user) {
        return new UserModel(
                user.getId(),
                user.getUsername(),
                user.getNickname()
        );
    }
}
