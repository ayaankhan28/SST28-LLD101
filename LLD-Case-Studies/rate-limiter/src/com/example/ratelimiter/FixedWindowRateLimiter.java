package com.example.ratelimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FixedWindowRateLimiter implements RateLimiter {
    private final int maxRequests;
    private final long windowSizeMs;
    private final Map<String, WindowCounter> counters;

    public FixedWindowRateLimiter(int maxRequests, long windowSizeMs) {
        this.maxRequests = maxRequests;
        this.windowSizeMs = windowSizeMs;
        this.counters = new ConcurrentHashMap<>();
    }

    @Override
    public boolean allowRequest(String userId) {
        long now = System.currentTimeMillis();
        WindowCounter counter = counters.computeIfAbsent(userId, 
            k -> new WindowCounter());

        synchronized (counter) {
            if (now - counter.windowStart > windowSizeMs) {
                counter.count = 0;
                counter.windowStart = now;
            }

            if (counter.count < maxRequests) {
                counter.count++;
                return true;
            }
            return false;
        }
    }

    @Override
    public void reset(String userId) {
        counters.remove(userId);
    }

    private static class WindowCounter {
        int count;
        long windowStart;

        WindowCounter() {
            this.count = 0;
            this.windowStart = System.currentTimeMillis();
        }
    }
}
