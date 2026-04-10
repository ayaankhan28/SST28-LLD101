package com.example.pen;

public class FountainPen extends Pen {
    private final String nibMaterial;

    public FountainPen(String brand, String name, Refill refill, String nibMaterial) {
        super(brand, name, PenType.FOUNTAIN, refill);
        this.nibMaterial = nibMaterial;
    }

    public String getNibMaterial() {
        return nibMaterial;
    }

    @Override
    public void write(String text) {
        if (!canWrite()) {
            throw new IllegalStateException("Fountain pen cannot write");
        }
        System.out.println("[Fountain Pen - Elegant writing with " + nibMaterial + " nib]");
        super.write(text);
    }
}
