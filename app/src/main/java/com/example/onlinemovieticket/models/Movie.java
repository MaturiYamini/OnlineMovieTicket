package com.example.onlinemovieticket.models;

public class Movie {
    private int id;
    private String title;
    private String description;
    private String imageUrl;
    private String duration;
    private String genre;
    private double rating;
    private double price;

    public Movie() {}

    public Movie(String title, String description, String imageUrl, String duration, String genre, double rating, double price) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.duration = duration;
        this.genre = genre;
        this.rating = rating;
        this.price = price;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}