package com.example.internship_eviapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ContactActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextMessage;
    private Button buttonSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ImageButton buttonBack = findViewById(R.id.button_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // Initialize views
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);

        // Set button click listener
        buttonSend.setOnClickListener(v -> sendMessage());
    }



    private void sendMessage() {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String message = editTextMessage.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || message.isEmpty()) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Here, you can handle the sending of the message, e.g., by calling an API or sending an email
        Toast.makeText(this, "Message sent successfully!", Toast.LENGTH_SHORT).show();
        clearFields();
    }

    private void clearFields() {
        editTextName.setText("");
        editTextEmail.setText("");
        editTextMessage.setText("");
    }
}
