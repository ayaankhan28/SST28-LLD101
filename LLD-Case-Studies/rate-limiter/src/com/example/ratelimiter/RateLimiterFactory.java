package com.example.ratelimiter;

public class RateLimiterFactory {
    
    public enum RateLimiterType {
        TOKEN_BUCKET,
        SLIDING_WINDOW,
        FIXED_WINDOW,
        LEAKY_BUCKET
    }

    public static RateLimiter createRateLimiter(RateLimiterType type, int limit, long timeWindow) {
        switch (type) {
            case TOKEN_BUCKET:
                return new TokenBucketRateLimiter(limit, limit);
            case SLIDING_WINDOW:
                return new SlidingWindowRateLimiter(limit, timeWindow);
            case FIXED_WINDOW:
                return new FixedWindowRateLimiter(limit, timeWindow);
            case LEAKY_BUCKET:
                return new LeakyBucketRateLimiter(limit, limit);
            default:
                throw new IllegalArgumentException("Unknown rate limiter type: " + type);
        }
    }
}
