package com.flickspick.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserModel {
    private Long id;
    private String username;
    private String nickname;
}
