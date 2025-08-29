package com.dosse.bwentrain.auth;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Placeholder Firebase storage backend implemented in-memory for tests.
 */
public class FirebaseStorage implements CloudStorage {
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
