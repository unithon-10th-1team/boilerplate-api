package com.flickspick.auth.model;

import lombok.Data;

@Data
public class AuthUserImpl implements AuthUser {
    private final Long id;

    @Override
    public Long getId() {
        return this.id;
    }
}
