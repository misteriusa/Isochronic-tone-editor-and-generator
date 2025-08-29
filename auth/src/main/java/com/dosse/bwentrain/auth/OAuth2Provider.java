package com.dosse.bwentrain.auth;

import java.util.function.Supplier;

/**
 * Lightweight OAuth2 provider wrapper. For simplicity this class delegates token
 * retrieval to a {@link Supplier}. In real scenarios the supplier would perform
 * a device or authorization code flow.
 */
public class OAuth2Provider implements AuthProvider {
    private final Supplier<String> tokenSupplier;

    public OAuth2Provider(Supplier<String> tokenSupplier) {
        this.tokenSupplier = tokenSupplier;
    }

    @Override
    public String authenticate() throws Exception {
        String token = tokenSupplier.get();
        if (token == null || token.isEmpty()) {
            throw new Exception("Token supplier returned empty token");
        }
        return token;
    }
}
