package com.example.cache;

public class CacheNode<K, V> {
    private final String nodeId;
    private final Cache<K, V> cache;

    public CacheNode(String nodeId, int capacity, EvictionPolicy<K> evictionPolicy) {
        this.nodeId = nodeId;
        this.cache = new InMemoryCache<>(capacity, evictionPolicy);
    }

    public String getNodeId() {
        return nodeId;
    }

    public V get(K key) {
        return cache.get(key);
    }

    public void put(K key, V value) {
        cache.put(key, value);
    }

    public void remove(K key) {
        cache.remove(key);
    }

    public int size() {
        return cache.size();
    }

    @Override
    public String toString() {
        return "Node-" + nodeId + " " + cache.toString();
    }
}
