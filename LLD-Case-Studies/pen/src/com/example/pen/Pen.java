package com.example.pen;

public abstract class Pen {
    protected final String brand;
    protected final String name;
    protected final PenType type;
    protected Refill refill;

    public Pen(String brand, String name, PenType type, Refill refill) {
        this.brand = brand;
        this.name = name;
        this.type = type;
        this.refill = refill;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public PenType getType() {
        return type;
    }

    public Refill getRefill() {
        return refill;
    }

    public void changeRefill(Refill newRefill) {
        this.refill = newRefill;
    }

    public boolean canWrite() {
        return refill != null && refill.canWrite();
    }

    public void write(String text) {
        if (!canWrite()) {
            throw new IllegalStateException("Pen cannot write");
        }
        refill.write(text);
        System.out.println("Writing with " + type + " pen: " + text);
    }

    @Override
    public String toString() {
        return brand + " " + name + " (" + type + ") - " + 
               (refill != null ? refill.toString() : "No refill");
    }
}
