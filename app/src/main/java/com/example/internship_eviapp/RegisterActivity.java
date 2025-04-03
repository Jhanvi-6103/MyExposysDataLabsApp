package com.example.internship_eviapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.internship_eviapp.PaymentActivity;
import com.example.internship_eviapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextRegisterEmail, editTextRegisterPassword;
    private ProgressBar progressBar;
    private FirebaseAuth authProfile;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextRegisterEmail = findViewById(R.id.editText_register_email);
        editTextRegisterPassword = findViewById(R.id.editText_register_password);
        progressBar = findViewById(R.id.progressBar);
        authProfile = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        // Handle back arrow button click
        ImageButton buttonBack = findViewById(R.id.button_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close current activity and return to the previous one
            }
        });

        // Handle register button click
        Button buttonRegister = findViewById(R.id.button_register);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textEmail = editTextRegisterEmail.getText().toString().trim();
                String textPwd = editTextRegisterPassword.getText().toString().trim();

                if (TextUtils.isEmpty(textEmail)) {
                    Toast.makeText(RegisterActivity.this, "Please enter your email", Toast.LENGTH_LONG).show();
                    editTextRegisterEmail.setError("Email is required");
                    editTextRegisterEmail.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()) {
                    Toast.makeText(RegisterActivity.this, "Please enter a valid email", Toast.LENGTH_LONG).show();
                    editTextRegisterEmail.setError("Valid email is required");
                    editTextRegisterEmail.requestFocus();
                } else if (TextUtils.isEmpty(textPwd)) {
                    editTextRegisterPassword.setError("Password is required");
                    editTextRegisterPassword.requestFocus();
                } else {
                    showPaymentOptionsDialog(textEmail, textPwd);
                }
            }
        });
    }

    private void showPaymentOptionsDialog(final String email, final String password) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Payment Method")
                .setMessage("Select how you would like to proceed with payment:")
                .setPositiveButton("Online Payment", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Start PaymentActivity for online payment
                        startPaymentActivity(email, password);
                    }
                })
                .setNegativeButton("Offline Payment", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Implement offline payment logic if needed
                        registerUser(email, password); // For demo, proceed with registration directly
//                        Toast.makeText(RegisterActivity.this, "You are registered ", Toast.LENGTH_LONG).show();
                    }
                })
                .setCancelable(false)
                .show();
    }

    private void startPaymentActivity(String email, String password) {
        Intent intent = new Intent(RegisterActivity.this, PaymentActivity.class);
        startActivity(intent);
    }

    private void registerUser(String textEmail, String textPwd) {
        progressBar.setVisibility(View.VISIBLE);
        authProfile.createUserWithEmailAndPassword(textEmail, textPwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String userId = authProfile.getCurrentUser().getUid();
                    User user = new User(textEmail, userId);

                    databaseReference.child(userId).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Registration successful :)", Toast.LENGTH_LONG).show();
                                redirectToHome();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Registration failed. Please try again later.", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } else {
                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    Toast.makeText(RegisterActivity.this, "Registration Successfull.", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
            }
        });
    }
    private void redirectToHome() {
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
