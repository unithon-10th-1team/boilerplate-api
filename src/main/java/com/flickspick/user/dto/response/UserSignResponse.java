package com.flickspick.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSignResponse {
    private String nickname;
    private String token;
}
