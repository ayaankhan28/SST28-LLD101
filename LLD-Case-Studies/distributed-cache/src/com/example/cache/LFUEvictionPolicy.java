package com.example.cache;

import java.util.HashMap;
import java.util.Map;

public class LFUEvictionPolicy<K> implements EvictionPolicy<K> {
    private final Map<K, Integer> frequencies;

    public LFUEvictionPolicy() {
        this.frequencies = new HashMap<>();
    }

    @Override
    public void keyAccessed(K key) {
        frequencies.put(key, frequencies.getOrDefault(key, 0) + 1);
    }

    @Override
    public K evict() {
        if (frequencies.isEmpty()) {
            return null;
        }

        K lfuKey = null;
        int minFreq = Integer.MAX_VALUE;

        for (Map.Entry<K, Integer> entry : frequencies.entrySet()) {
            if (entry.getValue() < minFreq) {
                minFreq = entry.getValue();
                lfuKey = entry.getKey();
            }
        }

        if (lfuKey != null) {
            frequencies.remove(lfuKey);
        }
        return lfuKey;
    }

    @Override
    public void remove(K key) {
        frequencies.remove(key);
    }
}
