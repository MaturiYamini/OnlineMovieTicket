package com.example.onlinemovieticket.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.onlinemovieticket.R;

public class FeedbackActivity extends AppCompatActivity {
    private TextView tvFeedbackTitle;
    private RatingBar ratingBar;
    private EditText etFeedbackComment;
    private Button btnSubmitFeedback, btnSkipFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        initViews();
        setupClickListeners();
        
        boolean bookingConfirmed = getIntent().getBooleanExtra("booking_confirmed", false);
        if (bookingConfirmed) {
            tvFeedbackTitle.setText("Booking Confirmed! Please share your feedback");
        }
    }

    private void initViews() {
        tvFeedbackTitle = findViewById(R.id.tvFeedbackTitle);
        ratingBar = findViewById(R.id.ratingBar);
        etFeedbackComment = findViewById(R.id.etFeedbackComment);
        btnSubmitFeedback = findViewById(R.id.btnSubmitFeedback);
        btnSkipFeedback = findViewById(R.id.btnSkipFeedback);
    }

    private void setupClickListeners() {
        btnSubmitFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitFeedback();
            }
        });

        btnSkipFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToHome();
            }
        });
    }

    private void submitFeedback() {
        float rating = ratingBar.getRating();
        String comment = etFeedbackComment.getText().toString().trim();

        if (rating == 0) {
            Toast.makeText(this, "Please provide a rating", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save feedback (in real app, save to database)
        Toast.makeText(this, "Thank you for your feedback!", Toast.LENGTH_LONG).show();
        navigateToHome();
    }

    private void navigateToHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}