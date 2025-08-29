package com.example;

import com.dosse.bwentrain.auth.CloudStorage;
import com.dosse.bwentrain.auth.Session;
import com.dosse.bwentrain.auth.SessionStorage;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test verifying that {@link SessionStorage} retries failed cloud
 * operations before succeeding.
 */
public class SessionStorageIntegrationTest {

    /** Mock cloud storage that fails a configurable number of times before succeeding. */
    static class FlakyStorage implements CloudStorage {
        private final AtomicInteger attempts = new AtomicInteger();
        private final int failures;
        private byte[] stored;

        FlakyStorage(int failures) { this.failures = failures; }

        @Override
        public void save(String key, byte[] data) throws Exception {
            if (attempts.getAndIncrement() < failures) {
                throw new Exception("Transient error");
            }
            stored = data;
        }

        @Override
        public byte[] load(String key) {
            return stored;
        }
    }

    @Test
    public void retryWithBackoffEventuallySucceeds() throws Exception {
        FlakyStorage storage = new FlakyStorage(2);
        SessionStorage ss = new SessionStorage(storage);
        ss.saveSession("user", new Session("abc"));
        Session loaded = ss.loadSession("user");
        assertEquals("abc", loaded.getToken());
        assertEquals(3, storage.attempts.get());
    }
}
