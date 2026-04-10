package com.example.pen;

public class BallpointPen extends Pen {
    private final boolean clickable;

    public BallpointPen(String brand, String name, Refill refill, boolean clickable) {
        super(brand, name, PenType.BALLPOINT, refill);
        this.clickable = clickable;
    }

    public boolean isClickable() {
        return clickable;
    }

    @Override
    public void write(String text) {
        if (!canWrite()) {
            throw new IllegalStateException("Ballpoint pen cannot write");
        }
        System.out.println("[Ballpoint Pen - Reliable writing]");
        super.write(text);
    }
}
