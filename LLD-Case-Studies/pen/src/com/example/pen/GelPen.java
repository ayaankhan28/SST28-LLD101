package com.example.pen;

public class GelPen extends Pen {
    private final boolean retractable;

    public GelPen(String brand, String name, Refill refill, boolean retractable) {
        super(brand, name, PenType.GEL, refill);
        this.retractable = retractable;
    }

    public boolean isRetractable() {
        return retractable;
    }

    @Override
    public void write(String text) {
        if (!canWrite()) {
            throw new IllegalStateException("Gel pen cannot write");
        }
        System.out.println("[Gel Pen - Smooth writing]");
        super.write(text);
    }
}
