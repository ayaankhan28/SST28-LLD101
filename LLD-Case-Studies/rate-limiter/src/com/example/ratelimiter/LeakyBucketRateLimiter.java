package com.example.ratelimiter;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LeakyBucketRateLimiter implements RateLimiter {
    private final int capacity;
    private final int leakRate;
    private final Map<String, LeakyBucket> buckets;

    public LeakyBucketRateLimiter(int capacity, int leakRate) {
        this.capacity = capacity;
        this.leakRate = leakRate;
        this.buckets = new ConcurrentHashMap<>();
    }

    @Override
    public boolean allowRequest(String userId) {
        LeakyBucket bucket = buckets.computeIfAbsent(userId, 
            k -> new LeakyBucket(capacity, leakRate));
        return bucket.addRequest();
    }

    @Override
    public void reset(String userId) {
        buckets.remove(userId);
    }

    private static class LeakyBucket {
        private final int capacity;
        private final int leakRate;
        private final Queue<Long> requests;
        private long lastLeakTime;

        public LeakyBucket(int capacity, int leakRate) {
            this.capacity = capacity;
            this.leakRate = leakRate;
            this.requests = new ConcurrentLinkedQueue<>();
            this.lastLeakTime = System.currentTimeMillis();
        }

        public synchronized boolean addRequest() {
            leak();
            
            if (requests.size() < capacity) {
                requests.offer(System.currentTimeMillis());
                return true;
            }
            return false;
        }

        private void leak() {
            long now = System.currentTimeMillis();
            long elapsedSeconds = (now - lastLeakTime) / 1000;
            
            if (elapsedSeconds > 0) {
                int requestsToLeak = (int) (elapsedSeconds * leakRate);
                for (int i = 0; i < requestsToLeak && !requests.isEmpty(); i++) {
                    requests.poll();
                }
                lastLeakTime = now;
            }
        }
    }
}
