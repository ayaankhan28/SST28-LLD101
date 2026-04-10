package com.example.ratelimiter;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SlidingWindowRateLimiter implements RateLimiter {
    private final int maxRequests;
    private final long windowSizeMs;
    private final Map<String, Queue<Long>> requestTimestamps;

    public SlidingWindowRateLimiter(int maxRequests, long windowSizeMs) {
        this.maxRequests = maxRequests;
        this.windowSizeMs = windowSizeMs;
        this.requestTimestamps = new ConcurrentHashMap<>();
    }

    @Override
    public boolean allowRequest(String userId) {
        long now = System.currentTimeMillis();
        Queue<Long> timestamps = requestTimestamps.computeIfAbsent(userId, 
            k -> new ConcurrentLinkedQueue<>());

        synchronized (timestamps) {
            while (!timestamps.isEmpty() && now - timestamps.peek() > windowSizeMs) {
                timestamps.poll();
            }

            if (timestamps.size() < maxRequests) {
                timestamps.offer(now);
                return true;
            }
            return false;
        }
    }

    @Override
    public void reset(String userId) {
        requestTimestamps.remove(userId);
    }
}
