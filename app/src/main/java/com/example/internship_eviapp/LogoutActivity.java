package com.example.internship_eviapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LogoutActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        Button buttonConfirmLogout = findViewById(R.id.button_confirm_logout);
        Button buttonCancelLogout = findViewById(R.id.button_cancel_logout);

        buttonConfirmLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if user is logged in
                boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
                if (isLoggedIn) {
                    // Clear login state
                    sharedPreferences.edit().putBoolean("isLoggedIn", false).apply();

                    // Redirect to login or welcome screen
                    Intent intent = new Intent(LogoutActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();

                    // Display a toast message
                    Toast.makeText(LogoutActivity.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Display a toast message indicating the user is not logged in
                    Toast.makeText(LogoutActivity.this, "You are not logged in", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonCancelLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Simply finish the activity to return to the previous screen
                finish();
            }
        });
    }
}
