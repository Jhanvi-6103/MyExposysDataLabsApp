package com.example.internship_eviapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private SharedPreferences sharedPreferences;
    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        // Initialize DrawerLayout and ActionBarDrawerToggle
        drawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        // Set the drawer listener
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Set up the toolbar as the action bar and enable the drawer icon
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_hamburger);  // Set the hamburger icon
        }

        // Set Navigation Item Selected Listener
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_login) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            } else if (id == R.id.nav_registration) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            } else if (id == R.id.nav_menu) {
                startActivity(new Intent(MainActivity.this, MenuActivity.class));
            } else if (id == R.id.nav_aboutus) {
                startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
            } else if (id == R.id.nav_training) {
                startActivity(new Intent(MainActivity.this, TrainingActivity.class));
            } else if (id == R.id.nav_logout) {
                handleLogout();
            }
            drawerLayout.closeDrawers();
            return true;
        });

        // Find the contact button and set its click listener
        Button buttonContact = findViewById(R.id.button_contact);
        buttonContact.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ContactActivity.class);
            startActivity(intent);
        });

        // Find the internship button and set its click listener
        Button buttonInternship = findViewById(R.id.button_internship);
        buttonInternship.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, InternshipActivity.class));
        });

        // Initialize ViewPager and TabLayout
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        // List of images for the ViewPager
        List<Integer> images = Arrays.asList(
                R.drawable.image1,// Replace with your actual image resources
                R.drawable.image2,
                R.drawable.image3,
                R.drawable.image4
        );

        // Set up the adapter
        ImageAdapter adapter = new ImageAdapter(this, images);
        viewPager.setAdapter(adapter);

        // Set up the TabLayout with the ViewPager
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {}).attach();
    }

    private void handleLogout() {
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        if (isLoggedIn) {
            // Clear login state
            sharedPreferences.edit().putBoolean("isLoggedIn", false).apply();

            // Redirect to login or welcome screen
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

            Toast.makeText(MainActivity.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "You are not logged in", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openInternshipActivity(View view) {
        startActivity(new Intent(MainActivity.this, InternshipActivity.class));
    }
}
