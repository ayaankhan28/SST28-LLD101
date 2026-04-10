package com.example.pen;

public class Ink {
    private final String color;
    private int quantity;
    private final int capacity;

    public Ink(String color, int capacity) {
        this.color = color;
        this.capacity = capacity;
        this.quantity = capacity;
    }

    public String getColor() {
        return color;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean hasInk() {
        return quantity > 0;
    }

    public void use(int amount) {
        if (amount > quantity) {
            throw new IllegalStateException("Not enough ink");
        }
        quantity -= amount;
    }

    public void refill() {
        quantity = capacity;
    }

    @Override
    public String toString() {
        return color + " ink (" + quantity + "/" + capacity + " ml)";
    }
}
