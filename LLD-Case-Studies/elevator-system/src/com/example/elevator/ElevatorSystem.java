package com.example.elevator;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem {
    private final List<ElevatorCar> elevators;
    private final DispatchStrategy dispatchStrategy;
    private final int totalFloors;

    public ElevatorSystem(int numberOfElevators, int totalFloors, DispatchStrategy dispatchStrategy) {
        this.elevators = new ArrayList<>();
        this.totalFloors = totalFloors;
        this.dispatchStrategy = dispatchStrategy;

        for (int i = 1; i <= numberOfElevators; i++) {
            elevators.add(new ElevatorCar(i, 0));
        }
    }

    public void requestElevator(int sourceFloor, int destinationFloor) {
        if (sourceFloor < 0 || sourceFloor >= totalFloors || 
            destinationFloor < 0 || destinationFloor >= totalFloors) {
            throw new IllegalArgumentException("Invalid floor number");
        }

        Request request = new Request(sourceFloor, destinationFloor);
        System.out.println("\n=== New Request: " + request + " ===");

        ElevatorCar selectedElevator = dispatchStrategy.selectElevator(elevators, request);
        
        if (selectedElevator == null) {
            System.out.println("No elevator available");
            return;
        }

        System.out.println("Assigned to Elevator " + selectedElevator.getId());
        selectedElevator.addStop(request.getSourceFloor());
        selectedElevator.addStop(request.getDestinationFloor());
    }

    public void step() {
        for (ElevatorCar elevator : elevators) {
            elevator.move();
        }
    }

    public void printStatus() {
        System.out.println("\n=== Elevator Status ===");
        for (ElevatorCar elevator : elevators) {
            System.out.println(elevator);
        }
    }

    public List<ElevatorCar> getElevators() {
        return elevators;
    }
}
