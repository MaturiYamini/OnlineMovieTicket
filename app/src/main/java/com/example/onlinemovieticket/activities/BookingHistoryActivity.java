package com.example.onlinemovieticket.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onlinemovieticket.R;
import com.example.onlinemovieticket.adapters.BookingHistoryAdapter;
import com.example.onlinemovieticket.models.Booking;
import com.example.onlinemovieticket.utils.DatabaseHelper;
import java.util.List;
import java.util.ArrayList;

public class BookingHistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerViewHistory;
    private BookingHistoryAdapter adapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_history);

        initViews();
        loadBookingHistory();
    }

    private void initViews() {
        recyclerViewHistory = findViewById(R.id.recyclerViewHistory);
        dbHelper = new DatabaseHelper(this);
    }

    private void loadBookingHistory() {
        try {
            SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
            String userEmail = prefs.getString("user_email", "");
            
            if (userEmail.isEmpty()) {
                Toast.makeText(this, "No user logged in", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }
            
            List<Booking> bookings = dbHelper.getUserBookings(userEmail);
            
            if (bookings == null) {
                bookings = new ArrayList<>();
            }
            
            adapter = new BookingHistoryAdapter(bookings);
            recyclerViewHistory.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewHistory.setAdapter(adapter);
            
            if (bookings.isEmpty()) {
                Toast.makeText(this, "No booking history found", Toast.LENGTH_SHORT).show();
            }
            
        } catch (Exception e) {
            Toast.makeText(this, "Error loading history: " + e.getMessage(), Toast.LENGTH_LONG).show();
            finish();
        }
    }
}