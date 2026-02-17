package com.example.onlinemovieticket.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.onlinemovieticket.R;
import com.example.onlinemovieticket.models.Booking;
import com.example.onlinemovieticket.utils.DatabaseHelper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class PaymentActivity extends AppCompatActivity {
    private TextView tvBookingSummary, tvTotalAmount;
    private RadioGroup rgPaymentMethods;
    private RadioButton rbCreditCard, rbDebitCard, rbUPI, rbNetBanking;
    private Button btnConfirmPayment;
    
    private String movieTitle, showTime;
    private ArrayList<String> selectedSeats;
    private double totalAmount;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        getIntentData();
        initViews();
        displayBookingSummary();
        setupClickListeners();
        dbHelper = new DatabaseHelper(this);
    }

    private void getIntentData() {
        movieTitle = getIntent().getStringExtra("movie_title");
        showTime = getIntent().getStringExtra("show_time");
        selectedSeats = getIntent().getStringArrayListExtra("selected_seats");
        totalAmount = getIntent().getDoubleExtra("total_amount", 0.0);
    }

    private void initViews() {
        tvBookingSummary = findViewById(R.id.tvBookingSummary);
        tvTotalAmount = findViewById(R.id.tvTotalAmount);
        rgPaymentMethods = findViewById(R.id.rgPaymentMethods);
        rbCreditCard = findViewById(R.id.rbCreditCard);
        rbDebitCard = findViewById(R.id.rbDebitCard);
        rbUPI = findViewById(R.id.rbUPI);
        rbNetBanking = findViewById(R.id.rbNetBanking);
        btnConfirmPayment = findViewById(R.id.btnConfirmPayment);
    }

    private void displayBookingSummary() {
        String summary = "Movie: " + movieTitle + "\n" +
                        "Show Time: " + showTime + "\n" +
                        "Seats: " + selectedSeats.toString() + "\n" +
                        "Number of Tickets: " + selectedSeats.size();
        
        tvBookingSummary.setText(summary);
        tvTotalAmount.setText("Total Amount: ₹" + totalAmount);
    }

    private void setupClickListeners() {
        btnConfirmPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmPayment();
            }
        });
    }

    private void confirmPayment() {
        int selectedPaymentId = rgPaymentMethods.getCheckedRadioButtonId();
        
        if (selectedPaymentId == -1) {
            Toast.makeText(this, "Please select a payment method", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedPayment = findViewById(selectedPaymentId);
        String paymentMethod = selectedPayment.getText().toString();

        // Save booking to database
        saveBooking(paymentMethod);
        
        // Simulate payment processing
        Toast.makeText(this, "Payment Successful!", Toast.LENGTH_LONG).show();
        
        // Navigate to feedback
        Intent intent = new Intent(this, FeedbackActivity.class);
        intent.putExtra("booking_confirmed", true);
        startActivity(intent);
        finish();
    }

    private void saveBooking(String paymentMethod) {
        try {
            SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
            String userEmail = prefs.getString("user_email", "");
            
            if (userEmail.isEmpty()) {
                Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
                return;
            }
            
            String currentDate = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(new Date());
            
            Booking booking = new Booking(userEmail, 0, movieTitle, showTime, selectedSeats, totalAmount, currentDate, paymentMethod);
            long result = dbHelper.addBooking(booking);
            
            if (result != -1) {
                Toast.makeText(this, "Booking saved successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to save booking", Toast.LENGTH_SHORT).show();
            }
            
        } catch (Exception e) {
            Toast.makeText(this, "Error saving booking: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}