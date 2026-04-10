package com.example.ratelimiter;

public class App {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Rate Limiter Demo ===\n");

        System.out.println("=== Token Bucket Rate Limiter ===");
        RateLimiter tokenBucket = new TokenBucketRateLimiter(5, 2);
        testRateLimiter(tokenBucket, "user1", 10);

        System.out.println("\n=== Fixed Window Rate Limiter ===");
        RateLimiter fixedWindow = new FixedWindowRateLimiter(5, 5000);
        testRateLimiter(fixedWindow, "user2", 10);

        System.out.println("\n=== Sliding Window Rate Limiter ===");
        RateLimiter slidingWindow = new SlidingWindowRateLimiter(5, 5000);
        testRateLimiter(slidingWindow, "user3", 10);

        System.out.println("\n=== Leaky Bucket Rate Limiter ===");
        RateLimiter leakyBucket = new LeakyBucketRateLimiter(5, 2);
        testRateLimiter(leakyBucket, "user4", 10);

        System.out.println("\n=== Testing with Factory ===");
        RateLimiter factoryLimiter = RateLimiterFactory.createRateLimiter(
            RateLimiterFactory.RateLimiterType.TOKEN_BUCKET, 3, 1000);
        testRateLimiter(factoryLimiter, "user5", 8);

        System.out.println("\n=== Testing Multiple Users ===");
        RateLimiter multiUserLimiter = new TokenBucketRateLimiter(3, 1);
        System.out.println("User A requests:");
        for (int i = 1; i <= 5; i++) {
            boolean allowed = multiUserLimiter.allowRequest("userA");
            System.out.println("  Request " + i + ": " + (allowed ? "ALLOWED" : "DENIED"));
        }
        
        System.out.println("\nUser B requests:");
        for (int i = 1; i <= 5; i++) {
            boolean allowed = multiUserLimiter.allowRequest("userB");
            System.out.println("  Request " + i + ": " + (allowed ? "ALLOWED" : "DENIED"));
        }
    }

    private static void testRateLimiter(RateLimiter limiter, String userId, int requests) 
            throws InterruptedException {
        for (int i = 1; i <= requests; i++) {
            boolean allowed = limiter.allowRequest(userId);
            System.out.println("Request " + i + ": " + (allowed ? "ALLOWED" : "DENIED"));
            
            if (i == 7) {
                System.out.println("  [Waiting 2 seconds...]");
                Thread.sleep(2000);
            }
        }
    }
}
