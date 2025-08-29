package com.dosse.bwentrain.auth;

import java.util.Objects;

/**
 * Represents an authenticated session. Contains only a token for brevity.
 */
public class Session {
    private final String token;

    public Session(String token) {
        this.token = Objects.requireNonNull(token, "token");
    }

    public String getToken() {
        return token;
    }
}
