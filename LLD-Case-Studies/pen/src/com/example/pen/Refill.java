package com.example.pen;

public class Refill {
    private final Ink ink;
    private final double tipSize;
    private boolean damaged;

    public Refill(Ink ink, double tipSize) {
        this.ink = ink;
        this.tipSize = tipSize;
        this.damaged = false;
    }

    public Ink getInk() {
        return ink;
    }

    public double getTipSize() {
        return tipSize;
    }

    public boolean isDamaged() {
        return damaged;
    }

    public void damage() {
        this.damaged = true;
    }

    public boolean canWrite() {
        return !damaged && ink.hasInk();
    }

    public void write(String text) {
        if (!canWrite()) {
            throw new IllegalStateException("Cannot write - refill damaged or out of ink");
        }
        int inkUsed = text.length();
        ink.use(inkUsed);
    }

    @Override
    public String toString() {
        return "Refill{tipSize=" + tipSize + "mm, " + ink + ", damaged=" + damaged + "}";
    }
}
