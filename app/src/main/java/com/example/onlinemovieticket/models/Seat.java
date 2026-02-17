package com.example.onlinemovieticket.models;

public class Seat {
    private int id;
    private String seatNumber;
    private boolean isBooked;
    private boolean isSelected;
    private String showTime;
    private int movieId;

    public Seat() {}

    public Seat(String seatNumber, boolean isBooked, String showTime, int movieId) {
        this.seatNumber = seatNumber;
        this.isBooked = isBooked;
        this.showTime = showTime;
        this.movieId = movieId;
        this.isSelected = false;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }

    public boolean isBooked() { return isBooked; }
    public void setBooked(boolean booked) { isBooked = booked; }

    public boolean isSelected() { return isSelected; }
    public void setSelected(boolean selected) { isSelected = selected; }

    public String getShowTime() { return showTime; }
    public void setShowTime(String showTime) { this.showTime = showTime; }

    public int getMovieId() { return movieId; }
    public void setMovieId(int movieId) { this.movieId = movieId; }
}