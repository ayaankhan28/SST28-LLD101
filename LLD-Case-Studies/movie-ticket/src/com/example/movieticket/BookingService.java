package com.example.movieticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingService {
    private final Map<String, Booking> bookings;
    private int bookingCounter;

    public BookingService() {
        this.bookings = new HashMap<>();
        this.bookingCounter = 0;
    }

    public Booking createBooking(Show show, List<String> seatNumbers, String userId) {
        List<Seat> selectedSeats = new ArrayList<>();
        int totalAmount = 0;

        for (String seatNumber : seatNumbers) {
            Seat seat = findSeat(show.getScreen(), seatNumber);
            if (seat == null) {
                throw new IllegalArgumentException("Seat not found: " + seatNumber);
            }
            if (!seat.isAvailable()) {
                throw new IllegalStateException("Seat not available: " + seatNumber);
            }
            selectedSeats.add(seat);
            totalAmount += seat.getType().getPrice();
        }

        for (Seat seat : selectedSeats) {
            seat.setStatus(SeatStatus.LOCKED);
        }

        String bookingId = "BKG-" + (++bookingCounter);
        Booking booking = new Booking(bookingId, show, selectedSeats, userId, totalAmount);
        bookings.put(bookingId, booking);

        return booking;
    }

    public void confirmBooking(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking == null) {
            throw new IllegalArgumentException("Booking not found: " + bookingId);
        }

        for (Seat seat : booking.getSeats()) {
            seat.setStatus(SeatStatus.BOOKED);
        }
        booking.setStatus(BookingStatus.CONFIRMED);
    }

    public void cancelBooking(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking == null) {
            throw new IllegalArgumentException("Booking not found: " + bookingId);
        }

        for (Seat seat : booking.getSeats()) {
            seat.setStatus(SeatStatus.AVAILABLE);
        }
        booking.setStatus(BookingStatus.CANCELLED);
    }

    private Seat findSeat(Screen screen, String seatNumber) {
        for (Seat seat : screen.getSeats()) {
            if (seat.getSeatNumber().equals(seatNumber)) {
                return seat;
            }
        }
        return null;
    }

    public Booking getBooking(String bookingId) {
        return bookings.get(bookingId);
    }
}
