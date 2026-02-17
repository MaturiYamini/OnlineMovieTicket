package com.example.onlinemovieticket.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onlinemovieticket.R;
import com.example.onlinemovieticket.adapters.MovieAdapter;
import com.example.onlinemovieticket.models.Movie;
import com.example.onlinemovieticket.models.Booking;
import com.example.onlinemovieticket.utils.DatabaseHelper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity implements MovieAdapter.OnMovieClickListener {
    private RecyclerView recyclerViewMovies;
    private MovieAdapter movieAdapter;
    private List<Movie> movieList;
    private Button btnHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();
        setupMovieList();
        setupRecyclerView();
        setupClickListeners();
    }

    private void initViews() {
        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        btnHistory = findViewById(R.id.btnHistory);
    }

    private void setupMovieList() {
        movieList = new ArrayList<>();
        // Updated movie list
        movieList.add(new Movie("Mr. Perfect", "Romantic comedy drama", "mr_perfect", "2h 32m", "Romance", 8.2, 200.0));
        movieList.add(new Movie("3 Idiots", "Comedy drama masterpiece", "three_idiots", "2h 50m", "Comedy", 8.4, 180.0));
        movieList.add(new Movie("The GirlFriend", "Romantic thriller", "the_girlfriend", "2h 15m", "Romance", 7.8, 150.0));
        movieList.add(new Movie("Dude", "Action comedy", "dude", "2h 5m", "Action", 7.5, 170.0));
        movieList.add(new Movie("Bahubali The Epic", "Epic historical fiction", "bahubali_epic", "4 30m", "Epic", 8.7, 250.0));
        movieList.add(new Movie("K-Ramp", "Action thriller", "k_ramp", "2h 20m", "Action", 7.9, 190.0));
    }

    private void setupRecyclerView() {
        movieAdapter = new MovieAdapter(movieList, this);
        recyclerViewMovies.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewMovies.setAdapter(movieAdapter);
    }

    private void setupClickListeners() {
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add a test booking first if none exists
                addTestBookingIfNeeded();
                showDatabaseInfo();
                startActivity(new Intent(HomeActivity.this, BookingHistoryActivity.class));
            }
        });
    }
    
    private void showDatabaseInfo() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String userEmail = prefs.getString("user_email", "");
        
        List<Booking> bookings = dbHelper.getUserBookings(userEmail);
        
        String info = "Database Info:\n";
        info += "User Email: " + userEmail + "\n";
        info += "Total Bookings: " + bookings.size() + "\n\n";
        
        for (int i = 0; i < bookings.size(); i++) {
            Booking booking = bookings.get(i);
            info += "Booking " + (i+1) + ":\n";
            info += "Movie: " + booking.getMovieTitle() + "\n";
            info += "Seats: " + String.join(", ", booking.getSelectedSeats()) + "\n";
            info += "Amount: ₹" + booking.getTotalAmount() + "\n";
            info += "Date: " + booking.getBookingDate() + "\n\n";
        }
        
        Toast.makeText(this, info, Toast.LENGTH_LONG).show();
    }
    
    private void addTestBookingIfNeeded() {
        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String userEmail = prefs.getString("user_email", "");
        
        if (!userEmail.isEmpty()) {
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            List<Booking> existingBookings = dbHelper.getUserBookings(userEmail);
            
            if (existingBookings.isEmpty()) {
                // Add a test booking
                String currentDate = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(new Date());
                List<String> testSeats = Arrays.asList("A1", "A2");
                
                Booking testBooking = new Booking(userEmail, 1, "Avengers: Endgame", "Morning Show (10:00 AM)", 
                                                 testSeats, 500.0, currentDate, "Credit Card");
                
                long result = dbHelper.addBooking(testBooking);
                if (result != -1) {
                    Toast.makeText(this, "Test booking added", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onMovieClick(Movie movie) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra("movie_id", movie.getId());
        intent.putExtra("movie_title", movie.getTitle());
        intent.putExtra("movie_price", movie.getPrice());
        intent.putExtra("movie_image", movie.getImageUrl());
        intent.putExtra("movie_description", movie.getDescription());
        startActivity(intent);
    }
}