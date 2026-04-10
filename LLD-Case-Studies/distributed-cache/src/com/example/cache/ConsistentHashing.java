package com.example.cache;

import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashing<T> {
    private final SortedMap<Integer, T> ring;
    private final int numberOfReplicas;

    public ConsistentHashing(int numberOfReplicas) {
        this.ring = new TreeMap<>();
        this.numberOfReplicas = numberOfReplicas;
    }

    public void addNode(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            int hash = hash(node.toString() + i);
            ring.put(hash, node);
        }
    }

    public void removeNode(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            int hash = hash(node.toString() + i);
            ring.remove(hash);
        }
    }

    public T getNode(String key) {
        if (ring.isEmpty()) {
            return null;
        }

        int hash = hash(key);
        SortedMap<Integer, T> tailMap = ring.tailMap(hash);
        
        int nodeHash = tailMap.isEmpty() ? ring.firstKey() : tailMap.firstKey();
        return ring.get(nodeHash);
    }

    private int hash(String key) {
        return key.hashCode();
    }

    public int getNodeCount() {
        return ring.size() / numberOfReplicas;
    }
}
