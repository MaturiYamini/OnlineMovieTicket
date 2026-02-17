package com.example.onlinemovieticket.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onlinemovieticket.R;
import com.example.onlinemovieticket.adapters.SeatAdapter;
import com.example.onlinemovieticket.models.Seat;
import java.util.ArrayList;
import java.util.List;

public class SeatSelectionActivity extends AppCompatActivity implements SeatAdapter.OnSeatClickListener {
    private RecyclerView recyclerViewSeats;
    private TextView tvMovieTitle, tvShowTime, tvSelectedSeats, tvTotalAmount;
    private Button btnProceedPayment;
    private SeatAdapter seatAdapter;
    private List<Seat> seatList;
    private List<String> selectedSeats;
    private String movieTitle, showTime;
    private double moviePrice;
    private int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);

        getIntentData();
        initViews();
        setupSeats();
        setupRecyclerView();
        setupClickListeners();
    }

    private void getIntentData() {
        movieId = getIntent().getIntExtra("movie_id", 0);
        movieTitle = getIntent().getStringExtra("movie_title");
        moviePrice = getIntent().getDoubleExtra("movie_price", 0.0);
        showTime = getIntent().getStringExtra("show_time");
        selectedSeats = new ArrayList<>();
    }

    private void initViews() {
        recyclerViewSeats = findViewById(R.id.recyclerViewSeats);
        tvMovieTitle = findViewById(R.id.tvMovieTitle);
        tvShowTime = findViewById(R.id.tvShowTime);
        tvSelectedSeats = findViewById(R.id.tvSelectedSeats);
        tvTotalAmount = findViewById(R.id.tvTotalAmount);
        btnProceedPayment = findViewById(R.id.btnProceedPayment);

        tvMovieTitle.setText(movieTitle);
        tvShowTime.setText(showTime);
    }

    private void setupSeats() {
        seatList = new ArrayList<>();
        // Generate seats A1-A10, B1-B10, C1-C10, D1-D10
        String[] rows = {"A", "B", "C", "D"};
        for (String row : rows) {
            for (int i = 1; i <= 10; i++) {
                String seatNumber = row + i;
                boolean isBooked = Math.random() < 0.2; // 20% chance of being booked
                seatList.add(new Seat(seatNumber, isBooked, showTime, movieId));
            }
        }
    }

    private void setupRecyclerView() {
        seatAdapter = new SeatAdapter(seatList, this);
        recyclerViewSeats.setLayoutManager(new GridLayoutManager(this, 10));
        recyclerViewSeats.setAdapter(seatAdapter);
    }

    private void setupClickListeners() {
        btnProceedPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedSeats.isEmpty()) {
                    Toast.makeText(SeatSelectionActivity.this, "Please select at least one seat", Toast.LENGTH_SHORT).show();
                    return;
                }
                proceedToPayment();
            }
        });
    }

    @Override
    public void onSeatClick(Seat seat) {
        if (seat.isBooked()) {
            Toast.makeText(this, "Seat already booked", Toast.LENGTH_SHORT).show();
            return;
        }

        if (seat.isSelected()) {
            selectedSeats.remove(seat.getSeatNumber());
        } else {
            selectedSeats.add(seat.getSeatNumber());
        }
        seat.setSelected(!seat.isSelected());
        seatAdapter.notifyDataSetChanged();
        updateUI();
    }

    private void updateUI() {
        tvSelectedSeats.setText("Selected: " + selectedSeats.toString());
        double totalAmount = selectedSeats.size() * moviePrice;
        tvTotalAmount.setText("Total: ₹" + totalAmount);
    }

    private void proceedToPayment() {
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra("movie_title", movieTitle);
        intent.putExtra("show_time", showTime);
        intent.putStringArrayListExtra("selected_seats", (ArrayList<String>) selectedSeats);
        intent.putExtra("total_amount", selectedSeats.size() * moviePrice);
        startActivity(intent);
    }
}