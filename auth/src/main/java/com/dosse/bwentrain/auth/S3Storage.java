package com.dosse.bwentrain.auth;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Simplified in-memory representation of an S3-like storage backend. Real
 * implementations would use the AWS SDK; this is just a placeholder enabling
 * unit tests without external dependencies.
 */
public class S3Storage implements CloudStorage {
    private final Map<String, byte[]> store = new ConcurrentHashMap<>();

    @Override
    public void save(String key, byte[] data) {
        store.put(key, data);
    }

    @Override
    public byte[] load(String key) {
        return store.get(key);
    }
}
