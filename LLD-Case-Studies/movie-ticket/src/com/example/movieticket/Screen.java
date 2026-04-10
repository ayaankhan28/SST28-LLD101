package com.example.movieticket;

import java.util.ArrayList;
import java.util.List;

public class Screen {
    private final String screenId;
    private final String name;
    private final List<Seat> seats;

    public Screen(String screenId, String name) {
        this.screenId = screenId;
        this.name = name;
        this.seats = new ArrayList<>();
    }

    public String getScreenId() {
        return screenId;
    }

    public String getName() {
        return name;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void addSeat(Seat seat) {
        seats.add(seat);
    }

    public List<Seat> getAvailableSeats() {
        List<Seat> available = new ArrayList<>();
        for (Seat seat : seats) {
            if (seat.isAvailable()) {
                available.add(seat);
            }
        }
        return available;
    }

    @Override
    public String toString() {
        return name + " (Screen " + screenId + ", " + seats.size() + " seats)";
    }
}
