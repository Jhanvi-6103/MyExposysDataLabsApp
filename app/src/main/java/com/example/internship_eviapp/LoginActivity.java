package com.example.internship_eviapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        // Initialize and assign variable
//        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
//
//        // Set Home selected
//        bottomNavigationView.setSelectedItemId(R.id.nav_home);
//
//        // Perform item selected listener
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//                switch(item.getItemId())
//                {
//                    case 0:
//                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
//                        overridePendingTransition(0,0);
//                        return true;
//                    case 1:
//                        return true;
//                    case 2:
//                        startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
//                        overridePendingTransition(0,0);
//                        return true;
//                    case 3:
//                        startActivity(new Intent(getApplicationContext(),MenuActivity.class));
//                        overridePendingTransition(0,0);
//                        return true;
//                }
//                return false;
//            }
//        });
        // Initialize views
        editTextEmail = findViewById(R.id.editText_login_email);
        editTextPassword = findViewById(R.id.editText_login_password);
        progressBar = findViewById(R.id.progressBar);

        // Handle back button click
        ImageButton buttonBack = findViewById(R.id.button_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Handle login button click
        Button buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        // Handle forgot password button click
        Button buttonForgotPassword = findViewById(R.id.button_forgot_password);
        buttonForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToForgotPassword();
            }
        });
    }

    // Method to handle login functionality
    private void login() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Validate email and password
        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        // Show progress bar
        progressBar.setVisibility(View.VISIBLE);

        // Simulate login process (replace with actual authentication)
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        // Simulate successful login
                        if (email.equals("user@example.com") && password.equals("password")) {
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                2000 // 2 seconds delay for demo purposes
        );
    }

    // Method to navigate to Forgot Password screen
    private void navigateToForgotPassword() {
        Intent intent = new Intent(LoginActivity.this, ForgotActivityPassword.class);
        startActivity(intent);
    }
}
