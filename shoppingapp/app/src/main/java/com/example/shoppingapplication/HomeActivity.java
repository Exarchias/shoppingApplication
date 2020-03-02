package com.example.shoppingapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // when you log in you should end up in this activity-abdul





setActivityLisener();

    }




  //this starts the nav view -abdul
    public void setActivityLisener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


// this method handles all the activities in the navigation.
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        int id = item.getItemId();

        if (id == R.id.navOrders) {
            //startActivity(new Intent(this, OrdersActivity.class));
        } else if (id == R.id.navProfile) {
            startActivity(new Intent(this, ProfileActivity.class));
        } else if (id == R.id.navNew) {
            // startActivity(new Intent(this, AddItem.class));
        } else if (id == R.id.navConfirm) {
            //startActivity(new Intent(this, CheckoutActivity.class));

        } else if (id == R.id.navSignOut) {

        }
        return true;

    }


        @Override
        public void onPointerCaptureChanged ( boolean hasCapture){

        }
    }

