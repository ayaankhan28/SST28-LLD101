package com.example.cache;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Distributed Cache Demo ===\n");

        System.out.println("=== Testing In-Memory Cache with LRU ===");
        Cache<String, String> lruCache = new InMemoryCache<>(3, new LRUEvictionPolicy<>());
        
        lruCache.put("key1", "value1");
        lruCache.put("key2", "value2");
        lruCache.put("key3", "value3");
        System.out.println("Cache after 3 puts: " + lruCache);
        
        lruCache.get("key1");
        System.out.println("Accessed key1");
        
        lruCache.put("key4", "value4");
        System.out.println("Cache after adding key4: " + lruCache);
        System.out.println();

        System.out.println("=== Testing In-Memory Cache with LFU ===");
        Cache<String, String> lfuCache = new InMemoryCache<>(3, new LFUEvictionPolicy<>());
        
        lfuCache.put("A", "1");
        lfuCache.put("B", "2");
        lfuCache.put("C", "3");
        lfuCache.get("A");
        lfuCache.get("A");
        lfuCache.get("B");
        System.out.println("Cache: " + lfuCache);
        
        lfuCache.put("D", "4");
        System.out.println("After adding D (C should be evicted): " + lfuCache);
        System.out.println();

        System.out.println("=== Testing In-Memory Cache with FIFO ===");
        Cache<String, String> fifoCache = new InMemoryCache<>(3, new FIFOEvictionPolicy<>());
        
        fifoCache.put("X", "10");
        fifoCache.put("Y", "20");
        fifoCache.put("Z", "30");
        System.out.println("Cache: " + fifoCache);
        
        fifoCache.put("W", "40");
        System.out.println("After adding W (X should be evicted): " + fifoCache);
        System.out.println();

        System.out.println("=== Testing Distributed Cache ===");
        DistributedCache<String, String> distributedCache = new DistributedCache<>(5, 3);
        
        distributedCache.addNode("Node1");
        distributedCache.addNode("Node2");
        distributedCache.addNode("Node3");
        System.out.println();

        distributedCache.put("user:1", "Alice");
        distributedCache.put("user:2", "Bob");
        distributedCache.put("user:3", "Charlie");
        distributedCache.put("user:4", "David");
        distributedCache.put("user:5", "Eve");
        distributedCache.put("product:1", "Laptop");
        distributedCache.put("product:2", "Phone");
        distributedCache.put("order:1", "Order123");

        distributedCache.printStatus();

        System.out.println("\n=== Getting Values ===");
        System.out.println("user:1 = " + distributedCache.get("user:1"));
        System.out.println("product:1 = " + distributedCache.get("product:1"));
        System.out.println("order:1 = " + distributedCache.get("order:1"));

        System.out.println("\n=== Removing Node2 ===");
        distributedCache.removeNode("Node2");
        distributedCache.printStatus();

        System.out.println("\n=== Adding Node4 ===");
        distributedCache.addNode("Node4");
        distributedCache.put("user:6", "Frank");
        distributedCache.put("user:7", "Grace");
        distributedCache.printStatus();
    }
}
