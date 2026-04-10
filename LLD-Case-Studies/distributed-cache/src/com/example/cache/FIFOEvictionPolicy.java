package com.example.cache;

import java.util.LinkedList;
import java.util.Queue;

public class FIFOEvictionPolicy<K> implements EvictionPolicy<K> {
    private final Queue<K> queue;

    public FIFOEvictionPolicy() {
        this.queue = new LinkedList<>();
    }

    @Override
    public void keyAccessed(K key) {
        if (!queue.contains(key)) {
            queue.offer(key);
        }
    }

    @Override
    public K evict() {
        return queue.poll();
    }

    @Override
    public void remove(K key) {
        queue.remove(key);
    }
}
