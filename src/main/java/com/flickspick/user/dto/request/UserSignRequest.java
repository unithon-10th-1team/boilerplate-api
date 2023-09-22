package com.flickspick.user.dto.request;

import lombok.Data;

@Data
public class UserSignRequest {
    private String username;
    private String nickname;
    private String password;
}
