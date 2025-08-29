package com.dosse.bwentrain.auth;

/**
 * Defines a cross-platform authentication provider abstraction.
 */
public interface AuthProvider {
    /**
     * Perform authentication and return a session token.
     *
     * @return opaque session token
     * @throws Exception if authentication fails
     */
    String authenticate() throws Exception;
}
