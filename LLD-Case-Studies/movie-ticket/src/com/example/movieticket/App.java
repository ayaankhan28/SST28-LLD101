package com.example.movieticket;

import java.time.LocalDateTime;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Movie Ticket Booking System ===\n");

        Movie movie = new Movie("M1", "Inception", "Sci-Fi", 148);
        System.out.println("Movie: " + movie);

        Screen screen = new Screen("S1", "Audi 1");
        screen.addSeat(new Seat("A1", 1, 1, SeatType.REGULAR));
        screen.addSeat(new Seat("A2", 1, 2, SeatType.REGULAR));
        screen.addSeat(new Seat("A3", 1, 3, SeatType.REGULAR));
        screen.addSeat(new Seat("B1", 2, 1, SeatType.PREMIUM));
        screen.addSeat(new Seat("B2", 2, 2, SeatType.PREMIUM));
        screen.addSeat(new Seat("C1", 3, 1, SeatType.VIP));
        screen.addSeat(new Seat("C2", 3, 2, SeatType.VIP));

        System.out.println("Screen: " + screen);
        System.out.println();

        Show show = new Show("SH1", movie, screen, LocalDateTime.of(2026, 4, 10, 18, 0));
        System.out.println("Show: " + show);
        System.out.println();

        BookingService bookingService = new BookingService();

        System.out.println("=== Available Seats ===");
        for (Seat seat : screen.getAvailableSeats()) {
            System.out.println("  " + seat);
        }
        System.out.println();

        System.out.println("=== Creating Booking 1 ===");
        Booking booking1 = bookingService.createBooking(show, List.of("A1", "A2"), "user123");
        System.out.println("Created: " + booking1);
        System.out.println();

        System.out.println("=== Confirming Booking 1 ===");
        bookingService.confirmBooking(booking1.getBookingId());
        System.out.println("Confirmed: " + booking1);
        System.out.println();

        System.out.println("=== Creating Booking 2 ===");
        Booking booking2 = bookingService.createBooking(show, List.of("B1", "C1"), "user456");
        System.out.println("Created: " + booking2);
        System.out.println();

        System.out.println("=== Available Seats After Bookings ===");
        for (Seat seat : screen.getAvailableSeats()) {
            System.out.println("  " + seat);
        }
        System.out.println();

        System.out.println("=== Cancelling Booking 1 ===");
        bookingService.cancelBooking(booking1.getBookingId());
        System.out.println("Cancelled: " + booking1);
        System.out.println();

        System.out.println("=== Available Seats After Cancellation ===");
        for (Seat seat : screen.getAvailableSeats()) {
            System.out.println("  " + seat);
        }
        System.out.println();

        System.out.println("=== Trying to Book Already Locked Seat ===");
        try {
            bookingService.createBooking(show, List.of("B1"), "user789");
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
