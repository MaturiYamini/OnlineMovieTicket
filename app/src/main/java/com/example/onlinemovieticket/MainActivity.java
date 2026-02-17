package com.example.onlinemovieticket;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.onlinemovieticket.activities.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Direct redirect to LoginActivity without loading any layout
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

}