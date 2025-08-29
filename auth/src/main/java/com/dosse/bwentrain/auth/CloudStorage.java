package com.dosse.bwentrain.auth;

/**
 * Abstraction over cloud storage backends capable of saving and loading session
 * data.
 */
public interface CloudStorage {
    void save(String key, byte[] data) throws Exception;

    byte[] load(String key) throws Exception;
}
