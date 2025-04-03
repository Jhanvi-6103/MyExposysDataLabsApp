package com.example.internship_eviapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        // Handle back button click
        ImageButton buttonBack = findViewById(R.id.button_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // Initialize views
        TextView textViewAboutUsDescription = findViewById(R.id.textView_about_us_description);
        TextView textViewOurMissionTitle = findViewById(R.id.textView_our_mission_title);
        TextView textViewOurMissionDescription = findViewById(R.id.textView_our_mission_description);
        TextView textViewGeneralInquiry = findViewById(R.id.textView_general_inquiry);
        TextView textViewJobsInquiry = findViewById(R.id.textView_jobs_inquiry);

        // Set texts
        textViewOurMissionTitle.setText("Our Mission");

        // You can set the descriptions programmatically if needed, or keep them static as in XML
    }
    public void openAboutUsActivity(View view) {
        Intent intent = new Intent(this, AboutUsActivity.class);
        startActivity(intent);
    }
}
