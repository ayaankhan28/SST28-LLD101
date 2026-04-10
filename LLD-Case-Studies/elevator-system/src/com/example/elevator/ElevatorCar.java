package com.example.elevator;

import java.util.TreeSet;

public class ElevatorCar {
    private final int id;
    private int currentFloor;
    private Direction direction;
    private ElevatorState state;
    private final TreeSet<Integer> upStops;
    private final TreeSet<Integer> downStops;

    public ElevatorCar(int id, int initialFloor) {
        this.id = id;
        this.currentFloor = initialFloor;
        this.direction = Direction.IDLE;
        this.state = ElevatorState.IDLE;
        this.upStops = new TreeSet<>();
        this.downStops = new TreeSet<>();
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public ElevatorState getState() {
        return state;
    }

    public void addStop(int floor) {
        if (floor > currentFloor) {
            upStops.add(floor);
        } else if (floor < currentFloor) {
            downStops.add(floor);
        }
    }

    public void move() {
        if (state == ElevatorState.IDLE) {
            if (!upStops.isEmpty()) {
                direction = Direction.UP;
                state = ElevatorState.MOVING;
            } else if (!downStops.isEmpty()) {
                direction = Direction.DOWN;
                state = ElevatorState.MOVING;
            }
        }

        if (state == ElevatorState.MOVING) {
            if (direction == Direction.UP) {
                if (!upStops.isEmpty() && upStops.first() == currentFloor) {
                    stop();
                } else if (!upStops.isEmpty()) {
                    currentFloor++;
                    System.out.println("Elevator " + id + " moving UP to floor " + currentFloor);
                } else if (!downStops.isEmpty()) {
                    direction = Direction.DOWN;
                } else {
                    state = ElevatorState.IDLE;
                    direction = Direction.IDLE;
                }
            } else if (direction == Direction.DOWN) {
                if (!downStops.isEmpty() && downStops.last() == currentFloor) {
                    stop();
                } else if (!downStops.isEmpty()) {
                    currentFloor--;
                    System.out.println("Elevator " + id + " moving DOWN to floor " + currentFloor);
                } else if (!upStops.isEmpty()) {
                    direction = Direction.UP;
                } else {
                    state = ElevatorState.IDLE;
                    direction = Direction.IDLE;
                }
            }
        }
    }

    private void stop() {
        state = ElevatorState.STOPPED;
        System.out.println("Elevator " + id + " STOPPED at floor " + currentFloor);
        
        if (direction == Direction.UP) {
            upStops.remove(currentFloor);
        } else if (direction == Direction.DOWN) {
            downStops.remove(currentFloor);
        }

        state = ElevatorState.MOVING;
    }

    public boolean isIdle() {
        return state == ElevatorState.IDLE && upStops.isEmpty() && downStops.isEmpty();
    }

    @Override
    public String toString() {
        return "Elevator-" + id + "{floor=" + currentFloor + ", dir=" + direction + 
               ", state=" + state + ", upStops=" + upStops + ", downStops=" + downStops + "}";
    }
}
