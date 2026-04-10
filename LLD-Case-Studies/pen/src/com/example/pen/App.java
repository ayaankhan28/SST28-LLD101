package com.example.pen;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Pen Design System Demo ===\n");

        Ink blueInk = new Ink("Blue", 100);
        Refill gelRefill = new Refill(blueInk, 0.7);
        GelPen gelPen = new GelPen("Pilot", "G2", gelRefill, true);

        System.out.println("Created: " + gelPen);
        gelPen.write("Hello World");
        System.out.println("After writing: " + gelPen + "\n");

        Ink blackInk = new Ink("Black", 150);
        Refill ballpointRefill = new Refill(blackInk, 1.0);
        BallpointPen ballpointPen = new BallpointPen("Parker", "Jotter", ballpointRefill, true);

        System.out.println("Created: " + ballpointPen);
        ballpointPen.write("Testing ballpoint");
        System.out.println("After writing: " + ballpointPen + "\n");

        Ink royalBlueInk = new Ink("Royal Blue", 200);
        Refill fountainRefill = new Refill(royalBlueInk, 0.5);
        FountainPen fountainPen = new FountainPen("Montblanc", "Meisterstück", fountainRefill, "Gold");

        System.out.println("Created: " + fountainPen);
        fountainPen.write("Elegant signature");
        System.out.println("After writing: " + fountainPen + "\n");

        Ink redInk = new Ink("Red", 80);
        Refill markerRefill = new Refill(redInk, 2.0);
        MarkerPen markerPen = new MarkerPen("Sharpie", "Ultra Fine", markerRefill, true);

        System.out.println("Created: " + markerPen);
        markerPen.write("Important Note");
        System.out.println("After writing: " + markerPen + "\n");

        System.out.println("=== Testing Refill Change ===");
        Ink newBlueInk = new Ink("Sky Blue", 100);
        Refill newRefill = new Refill(newBlueInk, 0.7);
        gelPen.changeRefill(newRefill);
        System.out.println("Changed refill: " + gelPen);
        gelPen.write("New refill test");

        System.out.println("\n=== Testing Ink Depletion ===");
        Ink limitedInk = new Ink("Green", 5);
        Refill limitedRefill = new Refill(limitedInk, 0.7);
        GelPen limitedPen = new GelPen("Generic", "Basic", limitedRefill, false);
        
        try {
            limitedPen.write("Short");
            System.out.println("Remaining: " + limitedPen.getRefill().getInk().getQuantity() + " ml");
            limitedPen.write("This text is too long for remaining ink");
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Testing Refill ===");
        limitedInk.refill();
        System.out.println("After refill: " + limitedPen);
        limitedPen.write("Works again!");
    }
}
