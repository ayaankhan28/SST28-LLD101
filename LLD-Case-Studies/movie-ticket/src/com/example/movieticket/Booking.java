package com.example.movieticket;

import java.time.LocalDateTime;
import java.util.List;

public class Booking {
    private final String bookingId;
    private final Show show;
    private final List<Seat> seats;
    private final String userId;
    private final LocalDateTime bookingTime;
    private final int totalAmount;
    private BookingStatus status;

    public Booking(String bookingId, Show show, List<Seat> seats, String userId, int totalAmount) {
        this.bookingId = bookingId;
        this.show = show;
        this.seats = seats;
        this.userId = userId;
        this.bookingTime = LocalDateTime.now();
        this.totalAmount = totalAmount;
        this.status = BookingStatus.PENDING;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Show getShow() {
        return show;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Booking{id=" + bookingId + ", show=" + show.getMovie().getTitle() + 
               ", seats=" + seats.size() + ", amount=Rs." + totalAmount + ", status=" + status + "}";
    }
}
