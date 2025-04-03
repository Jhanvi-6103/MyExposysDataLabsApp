package com.example.internship_eviapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.internship_eviapp.R;

public class TrainingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        // Initialize CardView
        CardView trainingCard = findViewById(R.id.training_card);

        // Set up click listeners for each training option TextView (if needed)
        TextView pythonMachineLearning = findViewById(R.id.python_machine_learning);
        TextView pythonNLP = findViewById(R.id.python_nlp);
        TextView pythonAI = findViewById(R.id.python_ai);
        TextView dataScience = findViewById(R.id.data_science);
        TextView cWithDataStructure = findViewById(R.id.c_with_data_structure);
        TextView dotNetFramework = findViewById(R.id.dot_net_framework);
        TextView webDevelopment = findViewById(R.id.web_development);
        TextView javaJ2EE = findViewById(R.id.java_j2ee);
        TextView chatbotDevelopment = findViewById(R.id.chatbot_development);

        // Example click listener for a training option
        pythonMachineLearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click event for Python with Machine Learning
                // Example: Navigate to a detailed activity or perform some action
            }
        });
        // Handle back button click
        ImageButton buttonBack = findViewById(R.id.button_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Add click listeners for other training options as needed
        // ...

    }
}
