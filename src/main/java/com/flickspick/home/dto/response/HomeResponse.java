package com.flickspick.home.dto.response;

import com.flickspick.user.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HomeResponse {
    private UserModel user;

}
