package com.example.pen;

public class MarkerPen extends Pen {
    private final boolean permanent;

    public MarkerPen(String brand, String name, Refill refill, boolean permanent) {
        super(brand, name, PenType.MARKER, refill);
        this.permanent = permanent;
    }

    public boolean isPermanent() {
        return permanent;
    }

    @Override
    public void write(String text) {
        if (!canWrite()) {
            throw new IllegalStateException("Marker pen cannot write");
        }
        System.out.println("[Marker Pen - " + (permanent ? "Permanent" : "Washable") + " marking]");
        super.write(text);
    }
}
