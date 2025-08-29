package com.dosse.bwentrain.auth;

import java.util.concurrent.Callable;

/**
 * Utility providing exponential backoff retry semantics for operations that may
 * fail transiently (e.g., network IO).
 */
public final class RetryUtils {
    private RetryUtils() {}

    public static <T> T retryWithBackoff(Callable<T> action, int maxAttempts, long initialDelayMs) throws Exception {
        long delay = initialDelayMs;
        Exception last = null;
        for (int i = 0; i < maxAttempts; i++) {
            try {
                return action.call();
            } catch (Exception ex) {
                last = ex;
                if (i == maxAttempts - 1) break;
                Thread.sleep(delay);
                delay *= 2; // exponential backoff
            }
        }
        throw last;
    }
}
