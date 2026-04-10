package com.example.cache;

import java.util.HashMap;
import java.util.Map;

public class DistributedCache<K, V> implements Cache<K, V> {
    private final Map<String, CacheNode<K, V>> nodes;
    private final ConsistentHashing<String> consistentHashing;
    private final int nodeCapacity;

    public DistributedCache(int nodeCapacity, int replicas) {
        this.nodes = new HashMap<>();
        this.consistentHashing = new ConsistentHashing<>(replicas);
        this.nodeCapacity = nodeCapacity;
    }

    public void addNode(String nodeId) {
        CacheNode<K, V> node = new CacheNode<>(nodeId, nodeCapacity, new LRUEvictionPolicy<>());
        nodes.put(nodeId, node);
        consistentHashing.addNode(nodeId);
        System.out.println("Added node: " + nodeId);
    }

    public void removeNode(String nodeId) {
        nodes.remove(nodeId);
        consistentHashing.removeNode(nodeId);
        System.out.println("Removed node: " + nodeId);
    }

    @Override
    public V get(K key) {
        String nodeId = consistentHashing.getNode(key.toString());
        if (nodeId == null) {
            return null;
        }
        CacheNode<K, V> node = nodes.get(nodeId);
        return node != null ? node.get(key) : null;
    }

    @Override
    public void put(K key, V value) {
        String nodeId = consistentHashing.getNode(key.toString());
        if (nodeId == null) {
            throw new IllegalStateException("No nodes available");
        }
        CacheNode<K, V> node = nodes.get(nodeId);
        if (node != null) {
            node.put(key, value);
            System.out.println("Put " + key + " -> " + value + " in " + nodeId);
        }
    }

    @Override
    public void remove(K key) {
        String nodeId = consistentHashing.getNode(key.toString());
        if (nodeId != null) {
            CacheNode<K, V> node = nodes.get(nodeId);
            if (node != null) {
                node.remove(key);
            }
        }
    }

    @Override
    public void clear() {
        for (CacheNode<K, V> node : nodes.values()) {
            node.remove(null);
        }
    }

    @Override
    public int size() {
        int total = 0;
        for (CacheNode<K, V> node : nodes.values()) {
            total += node.size();
        }
        return total;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public void printStatus() {
        System.out.println("\n=== Cache Status ===");
        for (CacheNode<K, V> node : nodes.values()) {
            System.out.println(node);
        }
    }
}
