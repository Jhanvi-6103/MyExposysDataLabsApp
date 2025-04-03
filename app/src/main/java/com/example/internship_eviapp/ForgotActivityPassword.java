package com.example.internship_eviapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotActivityPassword extends AppCompatActivity {

    private EditText editTextEmail;
    private Button buttonResetPassword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
// Handle back button click
        ImageButton buttonBack = findViewById(R.id.button_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // Initialize views
        editTextEmail = findViewById(R.id.editText_forgot_password_email);
        buttonResetPassword = findViewById(R.id.button_reset_password);
        progressBar = findViewById(R.id.progressBar);

        // Handle reset password button click
        buttonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        String email = editTextEmail.getText().toString().trim();

        // Validate email
        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        // Show progress bar
        progressBar.setVisibility(View.VISIBLE);

        // Simulate password reset process (replace with actual logic)
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        // Simulate successful password reset
                        Toast.makeText(ForgotActivityPassword.this, "Password reset link sent to " + email, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                },
                2000 // 2 seconds delay for demo purposes
        );
    }
}
