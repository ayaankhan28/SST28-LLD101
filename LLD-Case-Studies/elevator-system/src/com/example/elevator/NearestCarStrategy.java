package com.example.elevator;

import java.util.List;

public class NearestCarStrategy implements DispatchStrategy {

    @Override
    public ElevatorCar selectElevator(List<ElevatorCar> elevators, Request request) {
        ElevatorCar selected = null;
        int minDistance = Integer.MAX_VALUE;

        for (ElevatorCar elevator : elevators) {
            int distance = Math.abs(elevator.getCurrentFloor() - request.getSourceFloor());
            
            if (elevator.isIdle() && distance < minDistance) {
                minDistance = distance;
                selected = elevator;
            } else if (elevator.getDirection() == request.getDirection() && 
                       isOnTheWay(elevator, request) && distance < minDistance) {
                minDistance = distance;
                selected = elevator;
            }
        }

        if (selected == null && !elevators.isEmpty()) {
            selected = elevators.get(0);
        }

        return selected;
    }

    private boolean isOnTheWay(ElevatorCar elevator, Request request) {
        if (elevator.getDirection() == Direction.UP) {
            return request.getSourceFloor() >= elevator.getCurrentFloor();
        } else if (elevator.getDirection() == Direction.DOWN) {
            return request.getSourceFloor() <= elevator.getCurrentFloor();
        }
        return false;
    }
}
