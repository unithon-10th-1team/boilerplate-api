package com.flickspick.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSignResponse {
    private Long id;
    private String nickname;
}
