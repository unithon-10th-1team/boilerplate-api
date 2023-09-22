package com.flickspick.auth;

import com.flickspick.auth.model.AuthUser;
import org.springframework.lang.Nullable;

public class AuthUserValidator {
    public static boolean isAuthenticated(@Nullable AuthUser authUser) {
        return authUser != null;
    }

    public static Long getUidOrElseDefault(@Nullable AuthUser authUser) {
        if (isAuthenticated(authUser)) {
            return authUser.getId();
        }
        return -1L;
    }
}
