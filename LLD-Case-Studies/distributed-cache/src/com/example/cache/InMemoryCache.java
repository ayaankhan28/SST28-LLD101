package com.example.cache;

import java.util.HashMap;
import java.util.Map;

public class InMemoryCache<K, V> implements Cache<K, V> {
    private final int capacity;
    private final Map<K, V> storage;
    private final EvictionPolicy<K> evictionPolicy;

    public InMemoryCache(int capacity, EvictionPolicy<K> evictionPolicy) {
        this.capacity = capacity;
        this.storage = new HashMap<>();
        this.evictionPolicy = evictionPolicy;
    }

    @Override
    public synchronized V get(K key) {
        if (!storage.containsKey(key)) {
            return null;
        }
        evictionPolicy.keyAccessed(key);
        return storage.get(key);
    }

    @Override
    public synchronized void put(K key, V value) {
        if (storage.containsKey(key)) {
            storage.put(key, value);
            evictionPolicy.keyAccessed(key);
            return;
        }

        if (storage.size() >= capacity) {
            K evictKey = evictionPolicy.evict();
            if (evictKey != null) {
                storage.remove(evictKey);
                System.out.println("Evicted key: " + evictKey);
            }
        }

        storage.put(key, value);
        evictionPolicy.keyAccessed(key);
    }

    @Override
    public synchronized void remove(K key) {
        storage.remove(key);
        evictionPolicy.remove(key);
    }

    @Override
    public synchronized void clear() {
        storage.clear();
    }

    @Override
    public synchronized int size() {
        return storage.size();
    }

    @Override
    public synchronized boolean containsKey(K key) {
        return storage.containsKey(key);
    }

    @Override
    public String toString() {
        return "Cache{size=" + storage.size() + "/" + capacity + ", keys=" + storage.keySet() + "}";
    }
}
