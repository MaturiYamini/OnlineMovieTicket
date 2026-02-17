package com.example.onlinemovieticket.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.onlinemovieticket.R;

public class MovieDetailsActivity extends AppCompatActivity {
    private ImageView ivMoviePoster;
    private TextView tvMovieTitle, tvMovieDescription;
    private Button btnMorningShow, btnMatineeShow, btnMidnightShow;
    private String movieTitle, movieImage, movieDescription;
    private double moviePrice;
    private int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        getIntentData();
        initViews();
        setupClickListeners();
    }

    private void getIntentData() {
        movieId = getIntent().getIntExtra("movie_id", 0);
        movieTitle = getIntent().getStringExtra("movie_title");
        moviePrice = getIntent().getDoubleExtra("movie_price", 0.0);
        movieImage = getIntent().getStringExtra("movie_image");
        movieDescription = getIntent().getStringExtra("movie_description");
    }

    private void initViews() {
        ivMoviePoster = findViewById(R.id.ivMoviePoster);
        tvMovieTitle = findViewById(R.id.tvMovieTitle);
        tvMovieDescription = findViewById(R.id.tvMovieDescription);
        btnMorningShow = findViewById(R.id.btnMorningShow);
        btnMatineeShow = findViewById(R.id.btnMatineeShow);
        btnMidnightShow = findViewById(R.id.btnMidnightShow);

        tvMovieTitle.setText(movieTitle);
        tvMovieDescription.setText(movieDescription);
        
        // Set movie poster image
        if (movieImage != null) {
            int resourceId = getResources().getIdentifier(movieImage, "drawable", getPackageName());
            if (resourceId != 0) {
                ivMoviePoster.setImageResource(resourceId);
            }
        }
    }

    private void setupClickListeners() {
        btnMorningShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSeatSelection("Morning Show (10:00 AM)");
            }
        });

        btnMatineeShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSeatSelection("Matinee Show (2:00 PM)");
            }
        });

        btnMidnightShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSeatSelection("Midnight Show (11:00 PM)");
            }
        });
    }

    private void openSeatSelection(String showTime) {
        Intent intent = new Intent(this, SeatSelectionActivity.class);
        intent.putExtra("movie_id", movieId);
        intent.putExtra("movie_title", movieTitle);
        intent.putExtra("movie_price", moviePrice);
        intent.putExtra("show_time", showTime);
        startActivity(intent);
    }
}