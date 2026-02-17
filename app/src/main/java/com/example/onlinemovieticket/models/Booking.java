package com.example.onlinemovieticket.models;

import java.util.List;

public class Booking {
    private int id;
    private int userId;
    private String userEmail;
    private int movieId;
    private String movieTitle;
    private String showTime;
    private List<String> selectedSeats;
    private double totalAmount;
    private String bookingDate;
    private String paymentMethod;

    public Booking() {}

    public Booking(String userEmail, int movieId, String movieTitle, String showTime, 
                   List<String> selectedSeats, double totalAmount, String bookingDate, String paymentMethod) {
        this.userEmail = userEmail;
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.showTime = showTime;
        this.selectedSeats = selectedSeats;
        this.totalAmount = totalAmount;
        this.bookingDate = bookingDate;
        this.paymentMethod = paymentMethod;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public int getMovieId() { return movieId; }
    public void setMovieId(int movieId) { this.movieId = movieId; }

    public String getMovieTitle() { return movieTitle; }
    public void setMovieTitle(String movieTitle) { this.movieTitle = movieTitle; }

    public String getShowTime() { return showTime; }
    public void setShowTime(String showTime) { this.showTime = showTime; }

    public List<String> getSelectedSeats() { return selectedSeats; }
    public void setSelectedSeats(List<String> selectedSeats) { this.selectedSeats = selectedSeats; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getBookingDate() { return bookingDate; }
    public void setBookingDate(String bookingDate) { this.bookingDate = bookingDate; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
}