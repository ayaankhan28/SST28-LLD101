package com.example.elevator;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Elevator System Simulation ===");

        DispatchStrategy strategy = new NearestCarStrategy();
        ElevatorSystem system = new ElevatorSystem(3, 10, strategy);

        system.printStatus();

        system.requestElevator(0, 5);
        system.requestElevator(3, 7);
        system.requestElevator(8, 2);

        System.out.println("\n=== Simulation Steps ===");
        for (int i = 0; i < 15; i++) {
            System.out.println("\n--- Step " + (i + 1) + " ---");
            system.step();
        }

        system.printStatus();

        System.out.println("\n=== Adding More Requests ===");
        system.requestElevator(1, 9);
        system.requestElevator(6, 0);

        for (int i = 0; i < 20; i++) {
            System.out.println("\n--- Step " + (i + 16) + " ---");
            system.step();
        }

        system.printStatus();
    }
}
