package com.dosse.bwentrain.auth;

import java.nio.charset.StandardCharsets;

/**
 * Persists and loads {@link Session} objects from a {@link CloudStorage}
 * backend. All operations are wrapped with retry/backoff to handle transient
 * failures.
 */
public class SessionStorage {
    private final CloudStorage storage;

    public SessionStorage(CloudStorage storage) {
        this.storage = storage;
    }

    public void saveSession(String key, Session session) throws Exception {
        RetryUtils.retryWithBackoff(() -> {
            storage.save(key, session.getToken().getBytes(StandardCharsets.UTF_8));
            return null;
        }, 3, 100);
    }

    public Session loadSession(String key) throws Exception {
        byte[] data = RetryUtils.retryWithBackoff(() -> storage.load(key), 3, 100);
        if (data == null) {
            throw new Exception("Session not found");
        }
        return new Session(new String(data, StandardCharsets.UTF_8));
    }
}
