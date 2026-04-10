package com.example.movieticket;

public enum SeatType {
    REGULAR(100),
    PREMIUM(200),
    VIP(300);

    private final int price;

    SeatType(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
