package com.example.ratelimiter;

public interface RateLimiter {
    boolean allowRequest(String userId);
    void reset(String userId);
}
