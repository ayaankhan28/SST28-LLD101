package com.example.movieticket;

public class Seat {
    private final String seatNumber;
    private final int row;
    private final int column;
    private final SeatType type;
    private SeatStatus status;

    public Seat(String seatNumber, int row, int column, SeatType type) {
        this.seatNumber = seatNumber;
        this.row = row;
        this.column = column;
        this.type = type;
        this.status = SeatStatus.AVAILABLE;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public SeatType getType() {
        return type;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    public boolean isAvailable() {
        return status == SeatStatus.AVAILABLE;
    }

    @Override
    public String toString() {
        return seatNumber + " (" + type + ", " + status + ")";
    }
}
