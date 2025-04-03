package com.example.internship_eviapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {

    private TextView textViewPaymentAmount;
    private EditText editTextCardNumber, editTextExpiryDate, editTextCVV;
    private RadioGroup radioGroupPaymentMethods;
    private Button buttonProcessPayment, buttonCancelPayment;
    private ProgressBar progressBarPaymentDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
// Handle back button click
        ImageButton buttonBack = findViewById(R.id.button_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        textViewPaymentAmount = findViewById(R.id.textView_payment_amount);
        editTextCardNumber = findViewById(R.id.editText_card_number);
        editTextExpiryDate = findViewById(R.id.editText_expiry_date);
        editTextCVV = findViewById(R.id.editText_cvv);
        radioGroupPaymentMethods = findViewById(R.id.radio_group_payment_methods);
        buttonProcessPayment = findViewById(R.id.button_process_payment);
        buttonCancelPayment = findViewById(R.id.button_cancel_payment);
        progressBarPaymentDetails = findViewById(R.id.progressBar_payment_details);

        buttonProcessPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processPayment();
            }
        });

        buttonCancelPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelPayment();
            }
        });
    }

    private void processPayment() {
        String cardNumber = editTextCardNumber.getText().toString().trim();
        String expiryDate = editTextExpiryDate.getText().toString().trim();
        String cvv = editTextCVV.getText().toString().trim();

        // Validate card details here before proceeding

        int selectedId = radioGroupPaymentMethods.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(this, "Please select a payment method", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton radioButton = findViewById(selectedId);
        String paymentMethod = radioButton.getText().toString();

        // Simulate payment processing (replace with actual payment gateway integration)
        progressBarPaymentDetails.setVisibility(View.VISIBLE);
        // Here you would integrate with your payment gateway and handle the response accordingly
        // For demo purposes, just show a message and hide the progress bar after a delay
        Toast.makeText(this, "Processing payment with " + paymentMethod, Toast.LENGTH_SHORT).show();

        // Example delay before hiding progress bar (replace with actual payment processing)
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        progressBarPaymentDetails.setVisibility(View.GONE);
                        // Handle payment success or failure accordingly
                    }
                },
                3000 // 3 seconds delay
        );
    }

    private void cancelPayment() {
        // Redirect to home page or any other desired action
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); // Close this activity
    }
}
